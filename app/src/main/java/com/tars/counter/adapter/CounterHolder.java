package com.tars.counter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tars.counter.R;

/**
 * Created by lucasbonafe on 24/09/17.
 */

public class CounterHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView value;
    public View color;

    public CounterHolder(final View itemView, final CounterListener mCounterListener) {
        super(itemView);
        title = itemView.findViewById(R.id.counter_title_text_view);
        value = itemView.findViewById(R.id.counter_value_text_view);
        color = itemView.findViewById(R.id.counter_color_view);

        //Long click for remove
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mCounterListener.onLongClick(getAdapterPosition());
                return true;
            }
        });

        //Click for edit
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCounterListener.onClick(getAdapterPosition());
            }
        });
    }
}