package is.uncommon.playbook.sortedlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by ztang on 7/6/17.
 */
class ItemViewHolder extends RecyclerView.ViewHolder {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("MMM. d");

    private final TextView contentView;
    private final TextView dateView;
    private final TextView authorView;
    private final TextView categoryView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        contentView = (TextView) itemView.findViewById(R.id.content);
        dateView = (TextView) itemView.findViewById(R.id.date);
        authorView = (TextView) itemView.findViewById(R.id.author);
        categoryView = (TextView) itemView.findViewById(R.id.category);
    }

    public static ItemViewHolder create(ViewGroup parent) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article_row, parent, false);
        return new ItemViewHolder(view);
    }

    public void bindTo(final Article article, final ArticlesAdapter adapter) {
        contentView.setText(article.content());
        DateTime dateTime = new DateTime(article.publishedTime());
        dateView.setText(dateTime.toString(dateTimeFormatter));
        categoryView.setText(article.category());
        authorView.setText("By " + article.author());
    }
}
