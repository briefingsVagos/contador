package com.tars.contador.async;

import android.content.Context;
import android.os.AsyncTask;

import com.tars.contador.interfaces.AsyncTaskListener;
import com.tars.contador.model.Counter;
import com.tars.contador.model.CounterRoom;

import java.util.List;

/**
 * Created by lucasbonafe on 23/09/17.
 */

public class AsyncShowCounters extends AsyncTask<String, Integer, List<Counter>> {

    private AsyncTaskListener<List<Counter>> listener;
    private Context context;

    public AsyncShowCounters(AsyncTaskListener<List<Counter>> listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        listener.onTaskStart();
    }

    @Override
    protected List<Counter> doInBackground(String ... strings) {

        return CounterRoom.getInstance().getDatabase(context).counterDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Counter> result) {
        super.onPostExecute(result);
        listener.onTaskComplete(result);
    }
}
