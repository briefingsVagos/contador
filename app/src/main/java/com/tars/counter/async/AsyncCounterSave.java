package com.tars.counter.async;

import android.content.Context;
import android.os.AsyncTask;

import com.tars.counter.model.Counter;
import com.tars.counter.model.CounterRoom;

/**
 * Created by lucasbonafe on 23/09/17.
 */

public class AsyncCounterSave extends AsyncTask<String, Integer, Boolean> {

    private AsyncTaskListener<Boolean> listener;
    private Counter newCounter;
    private Context context;

    public AsyncCounterSave(AsyncTaskListener<Boolean> listener, Counter newCounter, Context context) {
        this.listener = listener;
        this.newCounter = newCounter;
        this.context = context;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        listener.onTaskStart();
    }

    @Override
    protected Boolean doInBackground(String ... strings) {

        CounterRoom.getInstance().getDatabase(context).counterDao().insert(newCounter);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        listener.onTaskComplete(result);
    }
}