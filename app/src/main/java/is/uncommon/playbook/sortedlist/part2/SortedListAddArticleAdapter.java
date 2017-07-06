package is.uncommon.playbook.sortedlist.part2;

import java.util.ArrayList;

import is.uncommon.playbook.sortedlist.Article;

public class SortedListAddArticleAdapter extends SortedListShouldBeSortedAdapter {

    public SortedListAddArticleAdapter(ArrayList<Article> articles) {
        super(articles);
    }

    public void addArticle(Article article) {
        articleSortedList.add(article);
    }
}
