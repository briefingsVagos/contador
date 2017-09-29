package com.tars.counter.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tars.counter.R;
import com.tars.counter.async.AsyncCounterSave;
import com.tars.counter.async.AsyncTaskListener;
import com.tars.counter.contract.MVP;
import com.tars.counter.model.Counter;

public class EditCountActivity extends AppCompatActivity implements MVP.PresenterEditCount {

    MVP.EditCountView editCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        //editCountView = new EditCountViewImpl(this, null);
        setContentView(editCountView.getRootView());

    }

    @Override
    public void saveAEditedCounter(Counter counter) {
        new AsyncCounterSave(new TaskCounterSave(), counter, getApplicationContext()).execute();
    }

    public class TaskCounterSave implements AsyncTaskListener<Boolean>
    {
        @Override
        public void onTaskStart() {
            // TODO show a progress here?
        }

        @Override
        public void onTaskComplete(Boolean result) {
            editCountView.saveAEditedCounter(result);
        }
    }
}