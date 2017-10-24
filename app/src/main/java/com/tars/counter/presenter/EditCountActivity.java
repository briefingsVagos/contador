package com.tars.counter.presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tars.counter.R;


public class EditCountActivity extends AppCompatActivity {

    TextView title;
    TextView number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_count);

        title = (TextView) findViewById(R.id.test1);
        number = (TextView) findViewById(R.id.test2);


        Bundle bundle = getIntent().getExtras();

        String titleToEdit = bundle.getString("COUNTER_TITLE");
        int numberToEdit = bundle.getInt("COUNTER_VALUE");

        title.setText(titleToEdit);
        number.setText(String.valueOf(numberToEdit));

    }
}
