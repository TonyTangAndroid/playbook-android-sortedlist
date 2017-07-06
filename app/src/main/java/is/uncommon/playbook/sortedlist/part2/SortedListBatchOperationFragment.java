package is.uncommon.playbook.sortedlist.part2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import is.uncommon.playbook.sortedlist.Article;
import is.uncommon.playbook.sortedlist.R;

public class SortedListBatchOperationFragment extends Fragment {
    private static final int DAYS_PRIOR = 20;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private View rootView;
    private Unbinder unbinder;
    private ArrayList<Article> articleList = new ArrayList<>();
    private SortedListBatchOperationsAdapter adapter;

    public static SortedListBatchOperationFragment create() {
        return new SortedListBatchOperationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =
                inflater.inflate(R.layout.fragment_sortedlist_batch_operations, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setupSeeds();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new SortedListBatchOperationsAdapter(articleList);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    private void setupSeeds() {
        for (int i = 0; i < DAYS_PRIOR; i++) {
            articleList.add(
                    Article.builder().publishedTime(DateTime.now().minusDays(i).getMillis()).build());
        }
    }

    @OnClick(R.id.performBatchOp)
    public void onPerformBatchOp() {
        adapter.performBatchOperation();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
