package com.tars.contador.view;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tars.contador.R;
import com.tars.contador.adapter.CounterAdapter;
import com.tars.contador.async.AsyncShowCounters;
import com.tars.contador.contract.MVP;
import com.tars.contador.interfaces.AsyncTaskListener;
import com.tars.contador.model.Counter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private View rootView;
    private List<Counter> mCounters;
    private CounterAdapter mAdapter;

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
}
