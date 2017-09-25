package com.tars.contador.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tars.contador.contract.MVP;
import com.tars.contador.view.NewCountViewImpl;

public class NewCountActivity extends AppCompatActivity implements MVP.PresenterNewCount {

    MVP.NewCountView newCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newCountView = new NewCountViewImpl(this, null);
        setContentView(newCountView.getRootView());
    }
}
