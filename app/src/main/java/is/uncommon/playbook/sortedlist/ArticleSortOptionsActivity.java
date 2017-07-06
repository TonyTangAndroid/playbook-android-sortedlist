package is.uncommon.playbook.sortedlist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by madhu on 08/01/17.
 */

public class ArticleSortOptionsActivity extends AppCompatActivity {
    public static final int SORT_TYPE_TIMESTAMP = 0;
    public static final int SORT_TYPE_CATEGORY = 1;
    public static final int SORT_TYPE_AUTHOR = 2;
    public static final int SORT_TYPE_CONTENT = 3;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private Unbinder binder;
    private ArticlesAdapter adapter;
    private ArticleDataset dataset;
    private int sortType = SORT_TYPE_TIMESTAMP;
    private int newSortType = sortType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_options);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binder = ButterKnife.bind(this);
        setupRecycler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort, menu);
        return true;
    }

    private void setupRecycler() {
        recyclerView.setLayoutManager(
                new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new ArticlesAdapter();
        dataset = new ArticleDataset(recyclerView, adapter);
        dataset.generateRandom();
        adapter.articleDataset(dataset);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_sort) {
            showSortOptionsDialog();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSortOptionsDialog() {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{
                        "Timestamp", "Category", "Author", "Content"
                });
        new AlertDialog.Builder(this).setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                ArticleSortOptionsActivity.this.newSortType = position;
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (ArticleSortOptionsActivity.this.newSortType
                        != ArticleSortOptionsActivity.this.sortType) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArticleSortOptionsActivity.this.sortType =
                                    ArticleSortOptionsActivity.this.newSortType;
                            ArticleSortOptionsActivity.this.dataset.changeSortType(
                                    ArticleSortOptionsActivity.this.sortType);
                        }
                    });
                }
            }
        }).show();
    }

    @Override
    protected void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

}
