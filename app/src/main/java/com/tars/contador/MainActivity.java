package com.tars.contador;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tars.contador.contract.MVP;
import com.tars.contador.contract.PresenterMainImpl;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MVP.MainView {

    private static MVP.PresenterMain presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (presenter == null) {
            presenter = new PresenterMainImpl();
        }
    }

}
