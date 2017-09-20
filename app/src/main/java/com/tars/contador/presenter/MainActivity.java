package com.tars.contador.presenter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tars.contador.R;
import com.tars.contador.contract.MVP;
import com.tars.contador.contract.PresenterMainImpl;
import com.tars.contador.model.Counter;
import com.tars.contador.model.CounterDB;
import com.tars.contador.model.DataBase;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MVP.MainView {

    private static MVP.PresenterMain presenter;
    private List<Counter> arrCounter;
    private DataBase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (presenter == null) {
            presenter = new PresenterMainImpl();
        }

        // Database test
        mDatabase = CounterDB.getInstance().getDataBase(getApplicationContext());

        new DatabaseTest().execute();
    }

    private class DatabaseTest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            Counter counter = new Counter("Sei la", 22, R.color.colorBlue);
            Counter counter2 = new Counter("Sei aqui", 2, R.color.colorOrange);

            mDatabase.counterDao().insert(counter);
            mDatabase.counterDao().insert(counter2);

            arrCounter = mDatabase.counterDao().getAll();

            for (Counter c : arrCounter) {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(c.getTitle());
                stringBuilder.append(c.getValue());
                stringBuilder.append(c.getColor());
                Log.d("db test", stringBuilder.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
