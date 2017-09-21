package com.tars.contador.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tars.contador.R;
import com.tars.contador.contract.MVP;
import com.tars.contador.model.Counter;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.tars.contador.R.id.new_counter_value_edit_text;

/**
 * View class that inflates the new_count_view
 * It was created to remove the view logic from Activities
 * that in this project should be treated as Presenters
 */
public class NewCountViewImpl implements MVP.NewCountView{

    private View rootView;
    Counter newCounter = new Counter("New counter", 0, 0);

    @InjectView(R.id.new_counter_title_edit_text)
    EditText newCounterTitleEditText;

    @InjectView(new_counter_value_edit_text)
    EditText newCounterValueEditText;

    @InjectView(R.id.new_counter_color_blue)
    FloatingActionButton newCounterColorBlue;

    @InjectView(R.id.new_counter_color_green)
    FloatingActionButton newCounterColorGreen;

    @InjectView(R.id.new_counter_color_red)
    FloatingActionButton newCounterColorRed;

    @InjectView(R.id.new_counter_color_orange)
    FloatingActionButton newCounterColorOrange;

    @InjectView(R.id.new_counter_color_yellow)
    FloatingActionButton newCounterColorYellow;

    @InjectView(R.id.new_counter_ok_button)
    Button newCounterOkButton;


    public NewCountViewImpl(Context context, ViewGroup container) {
        rootView = LayoutInflater.from(context).inflate(R.layout.new_count_view, container);

        ButterKnife.inject(this, rootView);

        newCounterValueEditText.setOnClickListener(newCounterEditText);
        newCounterTitleEditText.setOnClickListener(newCounterEditText);
        newCounterValueEditText.setOnClickListener(newCounterEditText);

        newCounterColorBlue.setOnClickListener(colorChose);
        newCounterColorGreen.setOnClickListener(colorChose);
        newCounterColorRed.setOnClickListener(colorChose);
        newCounterColorOrange.setOnClickListener(colorChose);
        newCounterColorYellow.setOnClickListener(colorChose);

        newCounterOkButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO validate and save in db
            }
        });
    }

    private OnClickListener colorChose = new OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.new_counter_color_blue:
                    newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorBlue));

                case R.id.new_counter_color_green:
                    newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorGreen));

                case R.id.new_counter_color_red:
                    newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorRed));

                case R.id.new_counter_color_orange:
                    newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorOrange));

                case R.id.new_counter_color_yellow:
                    newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorYellow));
            }
        }
    };


    private OnClickListener newCounterEditText = new OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.new_counter_title_edit_text:
                    newCounter.setTitle(newCounterTitleEditText.getText().toString());

                case new_counter_value_edit_text:
                    newCounter.setValue(Integer.valueOf(newCounterValueEditText.getText().toString()));
            }
        }
    };

    @Override
    public View getRootView() { return rootView; }

}
