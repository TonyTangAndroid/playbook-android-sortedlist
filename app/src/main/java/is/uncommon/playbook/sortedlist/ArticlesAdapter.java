package is.uncommon.playbook.sortedlist;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by ztang on 7/6/17.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private ArticleDataset dataset;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bindTo(dataset.getArticle(position), this);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void articleDataset(ArticleDataset dataset) {
        this.dataset = dataset;
    }

}
