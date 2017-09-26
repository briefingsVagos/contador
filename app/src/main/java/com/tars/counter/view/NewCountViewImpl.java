package com.tars.counter.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.tars.counter.R;
import com.tars.counter.async.AsyncCounterSave;
import com.tars.counter.contract.MVP;
import com.tars.counter.interfaces.AsyncTaskListener;
import com.tars.counter.model.Counter;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tars.counter.R.id.new_counter_value_edit_text;

/**
 * View class that inflates the new_count_view
 * It was created to remove the view logic from Activities
 * that in this project should be treated as Presenters
 */
public class NewCountViewImpl implements MVP.NewCountView{

    private View rootView;
    Counter newCounter = new Counter("New counter", 0, 0);

    @BindString(R.string.error_name_isempty) String errorNameIsEmpty;
    @BindString(R.string.default_value) String defaultValue;
    @BindString(R.string.toast_counter_saved_with_success) String toastSavedWithSuccess;

    @BindView(R.id.new_counter_title_edit_text) EditText newCounterTitleEditText;
    @BindView(new_counter_value_edit_text) EditText newCounterValueEditText;

    /*
    @BindView(R.id.new_counter_color_blue)
    FloatingActionButton newCounterColorBlue;

    @BindView(R.id.new_counter_color_green)
    FloatingActionButton newCounterColorGreen;

    @BindView(R.id.new_counter_color_red)
    FloatingActionButton newCounterColorRed;

    @BindView(R.id.new_counter_color_orange)
    FloatingActionButton newCounterColorOrange;

    @BindView(R.id.new_counter_color_yellow)
    FloatingActionButton newCounterColorYellow;

    @BindView(R.id.new_counter_ok_button)
    Button newCounterOkButton;
    */

    public NewCountViewImpl(Context context, ViewGroup container) {
        rootView = LayoutInflater.from(context).inflate(R.layout.new_count_view, container);

        ButterKnife.bind(this, rootView);


        /*
        newCounterColorBlue.setOnClickListener(colorChose);
        newCounterColorGreen.setOnClickListener(colorChose);
        newCounterColorRed.setOnClickListener(colorChose);
        newCounterColorOrange.setOnClickListener(colorChose);
        newCounterColorYellow.setOnClickListener(colorChose);

        */


    }

    @OnClick({ R.id.new_counter_color_blue, R.id.new_counter_color_green, R.id.new_counter_color_red,
            R.id.new_counter_color_orange, R.id.new_counter_color_yellow})
    public void colorChose(View view) {
        switch (view.getId()) {
            case R.id.new_counter_color_blue:
                newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorBlue));
                break;
            case R.id.new_counter_color_green:
                newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorGreen));
                break;
            case R.id.new_counter_color_red:
                newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorRed));
                break;
            case R.id.new_counter_color_orange:
                newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorOrange));
                break;
            case R.id.new_counter_color_yellow:
                newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorYellow));
                break;
        }
    }
    /*
    private OnClickListener colorChose = new OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.new_counter_color_blue:
                    newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorBlue));
                    break;
                case R.id.new_counter_color_green:
                    newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorGreen));
                    break;
                case R.id.new_counter_color_red:
                    newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorRed));
                    break;
                case R.id.new_counter_color_orange:
                    newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorOrange));
                    break;
                case R.id.new_counter_color_yellow:
                    newCounter.setColor(ContextCompat.getColor(view.getContext(), R.color.colorYellow));
                    break;
            }
        }
    };
    */


    @OnClick(R.id.new_counter_ok_button)
    public void submit(View view) {

        boolean valid = true;
        String finalName = newCounterTitleEditText.getText().toString();
        int finalValue = 0;

        if (finalName.trim().isEmpty()) {
            valid = false;
            newCounterTitleEditText.setError(NewCountViewImpl.this.errorNameIsEmpty);
        }
        else
            newCounterTitleEditText.setError(null);

        if (!newCounterValueEditText.getText().toString().trim().isEmpty())
            finalValue = Integer.valueOf(newCounterValueEditText.getText().toString());

        if (valid) {
            newCounter.setTitle(finalName);
            newCounter.setValue(finalValue);

            new AsyncCounterSave(new TaskCounterSave(), newCounter, view.getContext().getApplicationContext()).execute();
        }
    }

    /*
    newCounterOkButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean valid = true;
                String finalName = newCounterTitleEditText.getText().toString();
                int finalValue = 0;

                if (finalName.trim().isEmpty()) {
                    valid = false;
                    newCounterTitleEditText.setError(NewCountViewImpl.this.errorNameIsEmpty);
                }
                else
                    newCounterTitleEditText.setError(null);

                if (!newCounterValueEditText.getText().toString().trim().isEmpty())
                    finalValue = Integer.valueOf(newCounterValueEditText.getText().toString());

                if (valid) {
                    newCounter.setTitle(finalName);
                    newCounter.setValue(finalValue);

                    new AsyncCounterSave(new TaskCounterSave(), newCounter, view.getContext().getApplicationContext()).execute();
                }
            }
        });
     */



    @Deprecated
    private OnClickListener newCounterEditText = new OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.new_counter_title_edit_text:
                    newCounter.setTitle(newCounterTitleEditText.getText().toString());
                    break;
                case new_counter_value_edit_text:
                    String typeSafeNumber = newCounterValueEditText.getText().toString().trim();
                    newCounter.setValue(Integer.valueOf(typeSafeNumber.isEmpty()
                            ? "0"
                            : typeSafeNumber));
                    break;
            }
        }
    };

    @Override
    public View getRootView() { return rootView; }

    public class TaskCounterSave implements AsyncTaskListener<Boolean>
    {
        @Override
        public void onTaskStart() {
            // TODO show a progress here?
        }

        @Override
        public void onTaskComplete(Boolean result) {
            if (result)
            {
                Toast.makeText(rootView.getContext(), toastSavedWithSuccess, Toast.LENGTH_LONG).show();
            }
            else
            {
                // TODO unexpected error?
            }
        }
    }
}
