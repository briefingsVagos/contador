package com.tars.counter.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tars.counter.async.AsyncCounterSave;
import com.tars.counter.async.AsyncTaskListener;
import com.tars.counter.contract.MVP;
import com.tars.counter.model.Counter;
import com.tars.counter.view.MainViewImpl;

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

    }
}
