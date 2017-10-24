package com.tars.counter.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.tars.counter.async.AsyncCounterRemove;
import com.tars.counter.async.AsyncShowCounters;
import com.tars.counter.async.AsyncTaskListener;
import com.tars.counter.contract.MVP;
import com.tars.counter.model.Counter;
import com.tars.counter.view.MainViewImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MVP.PresenterMain {

    MVP.MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainView = new MainViewImpl(this, null);
        setContentView(mainView.getRootView());
    }

    @Override
    protected void onResume() {
        super.onResume();

        new AsyncShowCounters(new TaskShowCounters(), getApplicationContext()).execute();
    }

    @Override
    public void removeACounter(Counter removedCounter) {
        new AsyncCounterRemove(new TaskCounterRemove(), removedCounter, getApplicationContext()).execute();
    }

    @Override
    public void sendACounterToEdit (@NonNull Counter counterToEdit) {
        Bundle bundle = new Bundle();
        bundle.putString("COUNTER_TITLE", counterToEdit.getTitle());
        bundle.putInt("COUNTER_VALUE", counterToEdit.getValue());

        Intent intent = new Intent(this, EditCountActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public class TaskShowCounters implements AsyncTaskListener<List<Counter>>
    {
        @Override
        public void onTaskStart() {
            // TODO show a progress here?
        }

        @Override
        public void onTaskComplete(List<Counter> result) {
            mainView.showCounters(result);
        }
    }

    public class TaskCounterRemove implements AsyncTaskListener<Counter>
    {
        @Override
        public void onTaskStart() {
            // TODO show a progress here?
        }

        @Override
        public void onTaskComplete(Counter result) {
            mainView.removeACounter(result);
        }
    }
}
