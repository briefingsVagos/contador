package com.tars.counter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tars.counter.R;
import com.tars.counter.contract.MVP;
import com.tars.counter.model.Counter;
import com.tars.counter.presenter.MainActivity;

import java.util.List;

/**
 * Created by lucasbonafe on 24/09/17.
 */

public class CounterAdapter extends RecyclerView.Adapter<CounterHolder> implements CounterRemoveListener {

    private final List<Counter> mCounters;
    private MVP.PresenterMain presenter;

    public CounterAdapter(MainActivity presenter, List<Counter> mCounters) {
        this.presenter = presenter;
        this.mCounters = mCounters;
    }

    @Override
    public CounterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CounterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.counter_line_view, parent, false), this);
    }

    @Override
    public void onBindViewHolder(CounterHolder holder, int position) {
        holder.title.setText(mCounters.get(position).getTitle());
        holder.value.setText(String.valueOf(mCounters.get(position).getValue()));
        holder.color.setBackgroundColor(mCounters.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        return mCounters != null ? mCounters.size() : 0;
    }

    public void notifyItemRemoved(Counter counterRemoved) {

        int position = mCounters.indexOf(counterRemoved);

        notifyItemRemoved(position);
        mCounters.remove(position);
    }

    @Override
    public void onLongClick(int position) {
        presenter.removeACounter(mCounters.get(position));
    }
}