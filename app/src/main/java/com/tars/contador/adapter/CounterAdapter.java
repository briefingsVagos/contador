package com.tars.contador.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tars.contador.R;
import com.tars.contador.model.Counter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasbonafe on 24/09/17.
 */

public class CounterAdapter extends RecyclerView.Adapter<CounterHolder> {

    private final List<Counter> mCounters;

    public CounterAdapter(List<Counter> mCounters) {
        this.mCounters = mCounters;
    }

    @Override
    public CounterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CounterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.counter_line_view, parent, false));
    }

    @Override
    public void onBindViewHolder(CounterHolder holder, int position) {
        holder.title.setText(mCounters.get(position).getTitle());
        holder.value.setText(mCounters.get(position).getValue());

        // TODO some example of functional programming
//        holder.moreButton.setOnClickListener(view -> updateItem(position));
//        holder.deleteButton.setOnClickListener(view -> removerItem(position));
    }

    @Override
    public int getItemCount() {
        return mCounters != null ? mCounters.size() : 0;
    }

}
