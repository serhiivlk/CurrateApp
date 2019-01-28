package com.serhiiv.test.currate.ui.info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.serhiiv.test.currate.R;
import com.serhiiv.test.currate.core.base.BaseFragment;
import com.serhiiv.test.currate.ui.info.recycler.CurrencyRateAdapter;
import com.serhiiv.test.currate.ui.info.viewmodel.InfoViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InfoFragment extends BaseFragment {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.empty_view)
    View emptyView;
    @Inject
    InfoViewModel viewModel;
    private Unbinder unbinder;
    private CurrencyRateAdapter recyclerAdapter;

    @Inject
    public InfoFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerAdapter = new CurrencyRateAdapter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(recyclerAdapter);

        swipeRefreshLayout.setOnRefreshListener(viewModel::loadCurrencyRates);

        subscribeViewModel();
    }

    private void subscribeViewModel() {
        viewModel.isLoading().observe(this, swipeRefreshLayout::setRefreshing);
        viewModel.isEmpty().observe(this, isEmpty ->
                emptyView.setVisibility(isEmpty ? View.VISIBLE : View.GONE));
        viewModel.getRates().observe(this, recyclerAdapter::submitList);
        viewModel.getMessage().observe(this, message ->
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
