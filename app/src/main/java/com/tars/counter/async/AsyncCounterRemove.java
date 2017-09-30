package com.tars.counter.async;

import android.content.Context;
import android.os.AsyncTask;

import com.tars.counter.model.Counter;
import com.tars.counter.model.CounterRoom;

/**
 * Created by lucasbonafe on 23/09/17.
 */

public class AsyncCounterRemove extends AsyncTask<String, Integer, Counter> {

    private AsyncTaskListener<Counter> listener;
    private Counter removedCounter;
    private Context context;

    public AsyncCounterRemove(AsyncTaskListener<Counter> listener, Counter removedCounter, Context context) {
        this.listener = listener;
        this.removedCounter = removedCounter;
        this.context = context;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        listener.onTaskStart();
    }

    @Override
    protected Counter doInBackground(String ... strings) {

        CounterRoom.getInstance().getDatabase(context).counterDao().delete(removedCounter);
        return removedCounter;
    }

    @Override
    protected void onPostExecute(Counter result) {
        super.onPostExecute(result);
        listener.onTaskComplete(result);
    }
}