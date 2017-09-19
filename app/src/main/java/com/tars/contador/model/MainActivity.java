package com.tars.contador.model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tars.contador.R;
import com.tars.contador.contract.MVP;
import com.tars.contador.contract.PresenterMainImpl;

import java.util.List;

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

        // Database test
        DataBase dataBase = CounterDB.getInstance().getDataBase(getApplicationContext());

        Counter counter = new Counter("Sei la", 22, R.color.colorBlue);
        Counter counter2 = new Counter("Sei aqui", 2, R.color.colorOrange);

        dataBase.counterDao().insert(counter);
        dataBase.counterDao().insert(counter2);

        List<Counter> arrCounter = dataBase.counterDao().getAll();

        for (Counter c : arrCounter) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c.getTitle());
            stringBuilder.append(c.getValue());
            stringBuilder.append(c.getColor());
            Log.i("db test", stringBuilder.toString());
        }
    }

}
