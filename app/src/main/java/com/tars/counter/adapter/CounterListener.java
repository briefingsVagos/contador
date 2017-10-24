package com.tars.counter.adapter;

import android.view.View;

/**
 * Created by lucasbonafe on 30/09/17.
 */

public interface CounterListener {

    void onLongClick(int position);

    void onClick(int position);
}
