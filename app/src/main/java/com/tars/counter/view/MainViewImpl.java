package com.tars.counter.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tars.counter.R;
import com.tars.counter.adapter.CounterAdapter;
import com.tars.counter.contract.MVP;
import com.tars.counter.model.Counter;
import com.tars.counter.presenter.MainActivity;
import com.tars.counter.presenter.NewCounterActivity;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * View class that inflates the main_view
 * It was created to remove the view logic from Activities
 * that in this project should be treated as Presenters
 */
public class MainViewImpl implements MVP.MainView{

    private MVP.PresenterMain presenter;
    private View rootView;
    private List<Counter> mCounters;
    private CounterAdapter mAdapter;

    @BindView(R.id.counter_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.no_results_linear_layout) LinearLayout noResultsLinearLayout;
    @BindView(R.id.new_counter_fab) FloatingActionButton newCounterFab;

    @BindString(R.string.toast_counter_removed_with_success) String toastRemovedWithSuccess;

    public static final String REVEAL_X="REVEAL_X";
    public static final String REVEAL_Y="REVEAL_Y";

    public MainViewImpl(MainActivity presenter, ViewGroup container) {
        rootView = LayoutInflater.from(presenter).inflate(R.layout.main_view, container);

        ButterKnife.bind(this, rootView);
        this.presenter = presenter;
    }

    private void setRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new CounterAdapter((MainActivity) presenter, mCounters);
        mRecyclerView.setAdapter(mAdapter);

//        mRecyclerView.addItemDecoration(
//                new DividerItemDecoration(rootView.getContext(), DividerItemDecoration.VERTICAL));

        setRecyclerViewVisibility();
    }

    private void setRecyclerViewVisibility() {

        if (mCounters == null || mCounters.size() == 0) {
            mRecyclerView.setVisibility(View.GONE);
            noResultsLinearLayout.setVisibility(View.VISIBLE);
        }
        else {
            mRecyclerView.setVisibility(View.VISIBLE);
            noResultsLinearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public View getRootView() { return rootView; }

    @Override
    public void removeACounter(Counter removedCounter) {
        mAdapter.notifyItemRemoved(removedCounter);
        setRecyclerViewVisibility();
        Toast.makeText(rootView.getContext(), toastRemovedWithSuccess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCounters(List<Counter> mCounters) {
        this.mCounters = mCounters;
        setRecyclerView();
    }

    @OnClick(R.id.new_counter_fab)
    public void submit(View view) {

        int[] location = new int[2];
        newCounterFab.getLocationOnScreen(location);
        location[0] += newCounterFab.getWidth() / 2;
        location[1] += newCounterFab.getHeight() / 2;

        Intent intent = new Intent(rootView.getContext(), NewCounterActivity.class);
        intent.putExtra(REVEAL_X, location[0]);
        intent.putExtra(REVEAL_Y, location[1]);

        rootView.getContext().startActivity(intent);
    }
}