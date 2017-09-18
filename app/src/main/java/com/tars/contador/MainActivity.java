package com.tars.contador;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tars.contador.contract.MVP;
import com.tars.contador.contract.PresenterMainImpl;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements MVP.MainView {

    private static MVP.PresenterMain presenter;

    @InjectView(R.id.new_count_button)
    Button newCountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (presenter == null) {
            presenter = new PresenterMainImpl();
        }

        newCountButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });
    }

}
