package com.tars.contador.presenter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tars.contador.R;
import com.tars.contador.contract.MVP;
import com.tars.contador.model.Counter;
import com.tars.contador.model.CounterRoom;
import com.tars.contador.model.DB;
import com.tars.contador.view.MainViewImpl;
import com.tars.contador.view.NewCountViewImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MVP.PresenterMain {

    MVP.MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainView = new MainViewImpl(this, null);
        setContentView(mainView.getRootView());
    }
}
