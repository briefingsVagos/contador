package com.tars.counter.view;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.tars.counter.R;
import com.tars.counter.contract.MVP;
import com.tars.counter.model.Counter;
import com.tars.counter.presenter.NewCounterActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tars.counter.R.id.counter_color_purple;
import static com.tars.counter.R.id.counter_value_edit_text;
import static com.tars.counter.R.layout.counter_view;

/**
 * View class that inflates the counter_view
 * It was created to remove the view logic from Activities
 * that in this project should be treated as Presenters
 */
public class NewCounterViewImpl implements MVP.NewCountView{

    private PresenterNewCounter presenter;
    private View rootView;
    Counter newCounter = new Counter("New counter", 0, 0);

    @BindString(R.string.error_name_isempty) String errorNameIsEmpty;
    @BindString(R.string.default_value) String defaultValue;
    @BindString(R.string.toast_counter_saved_with_success) String toastSavedWithSuccess;

    @BindView(R.id.counter_title_edit_text) EditText counterTitleEditText;
    @BindView(counter_value_edit_text) EditText counterValueEditText;


    public NewCounterViewImpl(NewCounterActivity context, ViewGroup container) {
        rootView = LayoutInflater.from(context).inflate(counter_view, container);

        ButterKnife.bind(this, rootView);
        presenter = context;
    }

    @BindViews({
            R.id.counter_color_purple,
            R.id.counter_color_green,
            R.id.counter_color_red,
            R.id.counter_color_orange,
            R.id.counter_color_yellow})
    FloatingActionButton[] colors;

    private final ButterKnife.Action<FloatingActionButton> UNSELECT_ALL = new ButterKnife.Action<FloatingActionButton>() {
        @Override
        public void apply(@NonNull FloatingActionButton colorButton, int index) {
            colorButton.setImageResource(0);
        }
    };

    @OnClick({
            R.id.counter_color_purple,
            R.id.counter_color_green,
            R.id.counter_color_red,
            R.id.counter_color_orange,
            R.id.counter_color_yellow})
    public void colorChose(FloatingActionButton newCounterColor) {

        ButterKnife.apply(colors, UNSELECT_ALL);

        List<Resources> viewResources = new ArrayList<>();
        viewResources.add(rootView.getResources());

        newCounterColor.setImageDrawable(ContextCompat.getDrawable(getRootView().getContext(), R.drawable.ic_check_24dp));
        switch (newCounterColor.getId()) {
            case counter_color_purple:
                newCounter.setColor(ContextCompat.getColor(newCounterColor.getContext(), R.color.colorPurple));
                break;
            case R.id.counter_color_green:
                newCounter.setColor(ContextCompat.getColor(newCounterColor.getContext(), R.color.colorGreen));
                break;
            case R.id.counter_color_red:
                newCounter.setColor(ContextCompat.getColor(newCounterColor.getContext(), R.color.colorRed));
                break;
            case R.id.counter_color_orange:
                newCounter.setColor(ContextCompat.getColor(newCounterColor.getContext(), R.color.colorOrange));
                break;
            case R.id.counter_color_yellow:
                newCounter.setColor(ContextCompat.getColor(newCounterColor.getContext(), R.color.colorYellow));
                break;
        }
    }

    @OnClick(R.id.counter_ok_button)
    public void submit(View view) {

        boolean valid = true;
        String finalName = counterTitleEditText.getText().toString();
        int finalValue = 0;

        if (finalName.trim().isEmpty()) {
            valid = false;
            counterTitleEditText.setError(NewCounterViewImpl.this.errorNameIsEmpty);
        }
        else
            counterTitleEditText.setError(null);

        if (!counterValueEditText.getText().toString().trim().isEmpty())
            finalValue = Integer.valueOf(counterValueEditText.getText().toString());

        if (valid) {
            newCounter.setTitle(finalName);
            newCounter.setValue(finalValue);

            presenter.saveACounter(newCounter);
        }
    }

    @Deprecated
    private OnClickListener newCounterEditText = new OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.counter_title_edit_text:
                    newCounter.setTitle(counterTitleEditText.getText().toString());
                    break;
                case R.id.counter_value_edit_text:
                    String typeSafeNumber = counterValueEditText.getText().toString().trim();
                    newCounter.setValue(Integer.valueOf(typeSafeNumber.isEmpty()
                            ? "0"
                            : typeSafeNumber));
                    break;
            }
        }
    };

    @Override
    public View getRootView() { return rootView; }

    @Override
    public void saveACounter() {
        Toast.makeText(rootView.getContext(), toastSavedWithSuccess, Toast.LENGTH_SHORT).show();
        ((NewCounterActivity) presenter).finish();
    }
}