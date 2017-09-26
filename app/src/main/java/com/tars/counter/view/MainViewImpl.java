package com.tars.counter.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tars.counter.R;
import com.tars.counter.adapter.CounterAdapter;
import com.tars.counter.async.AsyncShowCounters;
import com.tars.counter.contract.MVP;
import com.tars.counter.interfaces.AsyncTaskListener;
import com.tars.counter.model.Counter;
import com.tars.counter.presenter.NewCountActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * View class that inflates the main_view
 * It was created to remove the view logic from Activities
 * that in this project should be treated as Presenters
 */
public class MainViewImpl implements MVP.MainView{

    @BindView(R.id.counter_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.no_results_linear_layout)
    LinearLayout noResultsLinearLayout;

    @BindView(R.id.new_counter_fab)
    FloatingActionButton newCounterFab;

    private View rootView;
    private List<Counter> mCounters;
    private CounterAdapter mAdapter;

    public static final String REVEAL_X="REVEAL_X";
    public static final String REVEAL_Y="REVEAL_Y";

    public MainViewImpl(Context context, ViewGroup container) {
        rootView = LayoutInflater.from(context).inflate(R.layout.main_view, container);

        ButterKnife.bind(this, rootView);

        // TODO Should be called in the event onResume || onStart?
        new AsyncShowCounters(new TaskShowCounters(), rootView.getContext().getApplicationContext()).execute();
    }

    private void setupRecycler() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new CounterAdapter(mCounters);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(rootView.getContext(), DividerItemDecoration.VERTICAL));

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

    public class TaskShowCounters implements AsyncTaskListener<List<Counter>>
    {
        @Override
        public void onTaskStart() {
            // TODO show a progress here?
        }

        @Override
        public void onTaskComplete(List<Counter> result) {
            mCounters = result;
            setupRecycler();
        }
    }

    @OnClick(R.id.new_counter_fab)
    public void submit(View view) {

        int[] location = new int[2];
        newCounterFab.getLocationOnScreen(location);
        location[0] += newCounterFab.getWidth() / 2;
        location[1] += newCounterFab.getHeight() / 2;

        Intent intent = new Intent(rootView.getContext(), NewCountActivity.class);
        intent.putExtra(REVEAL_X, location[0]);
        intent.putExtra(REVEAL_Y, location[1]);

        rootView.getContext().startActivity(intent);
    }
}
