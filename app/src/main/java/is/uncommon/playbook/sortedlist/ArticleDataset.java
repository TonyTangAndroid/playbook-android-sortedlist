package is.uncommon.playbook.sortedlist;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ztang on 7/6/17.
 */
public class ArticleDataset {

    private static final int DAYS_PRIOR = 20;
    SortedList<Article> sortedList = null;
    private int sortType = ArticleSortOptionsActivity.SORT_TYPE_TIMESTAMP;

    public ArticleDataset(final RecyclerView recyclerView, final RecyclerView.Adapter adapter) {
        this.sortedList = new SortedList<>(Article.class,
                new SortedList.BatchedCallback<>(new SortedListAdapterCallback<Article>(adapter) {
                    @Override
                    public int compare(Article a1, Article a2) {
                        return getComparator().compare(a1, a2);
                    }

                    @Override
                    public boolean areContentsTheSame(Article oldItem, Article newItem) {
                        return oldItem.areContentsTheSame(newItem);
                    }

                    @Override
                    public boolean areItemsTheSame(Article item1, Article item2) {
                        return item1.areItemsTheSame(item2);
                    }

                    @Override
                    public void onInserted(int position, int count) {
                        super.onInserted(position, count);
                        recyclerView.scrollToPosition(position);
                    }
                }));
    }

    public void generateRandom() {
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < DAYS_PRIOR; i++) {
            articleList.add(
                    Article.builder().publishedTime(DateTime.now().minusDays(i).getMillis()).build());
        }
        sortedList.beginBatchedUpdates();
        sortedList.addAll(articleList);
        sortedList.endBatchedUpdates();
    }

    public int size() {
        return sortedList.size();
    }

    public void changeSortType(int sortType) {
        this.sortType = sortType;
        List<Article> items = new ArrayList<>();
        for (int j = 0; j < sortedList.size(); j++) {
            items.add(sortedList.get(j));
        }
        sortedList.clear();
        sortedList.addAll(items);
        sortedList.endBatchedUpdates();
    }

    public Article getArticle(int position) {
        return sortedList.get(position);
    }

    public void remove(Article article) {
        sortedList.beginBatchedUpdates();
        sortedList.remove(article);
        sortedList.endBatchedUpdates();
    }

    public void add(Article article) {
        sortedList.beginBatchedUpdates();
        sortedList.add(article);
        sortedList.endBatchedUpdates();
    }

    private Comparator<Article> getComparator() {
        switch (sortType) {
            case ArticleSortOptionsActivity.SORT_TYPE_AUTHOR:
                return Article.authorComparator;
            case ArticleSortOptionsActivity.SORT_TYPE_CATEGORY:
                return Article.categoryComparator;
            case ArticleSortOptionsActivity.SORT_TYPE_CONTENT:
                return Article.contentComparator;
            case ArticleSortOptionsActivity.SORT_TYPE_TIMESTAMP:
            default:
                return Article.timestampComparator;
        }
    }
}
