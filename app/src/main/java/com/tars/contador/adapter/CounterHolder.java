package com.tars.contador.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tars.contador.R;

/**
 * Created by lucasbonafe on 24/09/17.
 */

public class CounterHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView value;

    public CounterHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.counter_title_text_view);
        value = itemView.findViewById(R.id.counter_value_text_view);
    }
}
