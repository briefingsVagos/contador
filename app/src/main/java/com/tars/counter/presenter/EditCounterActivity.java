package com.tars.counter.presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tars.counter.contract.MVP;
import com.tars.counter.model.Counter;
import com.tars.counter.view.EditViewImpl;


public class EditCounterActivity extends AppCompatActivity implements MVP.PresenterEditCounter {

    MVP.EditView editView;
    Counter counterToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        counterToEdit = new Counter(bundle.getString("COUNTER_TITLE"), bundle.getInt("COUNTER_VALUE"),
                bundle.getInt("COUNTER_COLOR"));

        editView = new EditViewImpl(this, null, counterToEdit);
        setContentView(editView.getRootView());
    }
}
