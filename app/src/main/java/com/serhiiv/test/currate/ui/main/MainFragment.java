package com.serhiiv.test.currate.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.serhiiv.test.currate.R;
import com.serhiiv.test.currate.core.base.BaseFragment;
import com.serhiiv.test.currate.ui.main.recycler.CurrencyPairAdapter;
import com.serhiiv.test.currate.ui.main.viewmodel.MainViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends BaseFragment {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.empty_view)
    View emptyView;

    private Unbinder unbinder;

    private CurrencyPairAdapter recyclerAdapter;

    @Inject
    MainViewModel viewModel;

    @Inject
    public MainFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerAdapter = new CurrencyPairAdapter(viewModel.getCheckedPairs(), viewModel::checkedPairs);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(recyclerAdapter);

        swipeRefreshLayout.setOnRefreshListener(() -> viewModel.loadCurrencyPairs());

        subscribeViewModel();
    }

    private void subscribeViewModel() {
        viewModel.isLoading().observe(this, isLoading ->
                swipeRefreshLayout.setRefreshing(isLoading));
        viewModel.getPairs().observe(this, pairs -> recyclerAdapter.submitList(pairs));
        viewModel.isEmpty().observe(this, isEmpty ->
                emptyView.setVisibility(isEmpty ? View.VISIBLE : View.GONE));
        viewModel.getMessage().observe(this, s ->
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
