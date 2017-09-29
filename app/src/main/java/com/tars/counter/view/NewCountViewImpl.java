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
import com.tars.counter.presenter.NewCountActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tars.counter.R.id.new_counter_color_purple;
import static com.tars.counter.R.id.new_counter_value_edit_text;
import static com.tars.counter.R.layout.new_count_view;

/**
 * View class that inflates the new_count_view
 * It was created to remove the view logic from Activities
 * that in this project should be treated as Presenters
 */
public class NewCountViewImpl implements MVP.NewCountView{

    private static MVP.PresenterNewCount presenter;
    private View rootView;
    Counter newCounter = new Counter("New counter", 0, 0);

    @BindString(R.string.error_name_isempty) String errorNameIsEmpty;
    @BindString(R.string.default_value) String defaultValue;
    @BindString(R.string.toast_counter_saved_with_success) String toastSavedWithSuccess;

    @BindView(R.id.new_counter_title_edit_text) EditText newCounterTitleEditText;
    @BindView(new_counter_value_edit_text) EditText newCounterValueEditText;


    public NewCountViewImpl(NewCountActivity context, ViewGroup container) {
        rootView = LayoutInflater.from(context).inflate(new_count_view, container);

        ButterKnife.bind(this, rootView);

        if (presenter == null)
            presenter = context;
    }

    @BindViews({
            R.id.new_counter_color_purple,
            R.id.new_counter_color_green,
            R.id.new_counter_color_red,
            R.id.new_counter_color_orange,
            R.id.new_counter_color_yellow})
    FloatingActionButton[] colors;

    final ButterKnife.Action<FloatingActionButton> UNSELECT_ALL = new ButterKnife.Action<FloatingActionButton>() {
        @Override
        public void apply(@NonNull FloatingActionButton colorButton, int index) {
            colorButton.setImageResource(0);
        }
    };

    @OnClick({
            R.id.new_counter_color_purple,
            R.id.new_counter_color_green,
            R.id.new_counter_color_red,
            R.id.new_counter_color_orange,
            R.id.new_counter_color_yellow})
    public void colorChose(FloatingActionButton newCounterColor) {

        ButterKnife.apply(colors, UNSELECT_ALL);

        List<Resources> viewResources = new ArrayList<>();
        viewResources.add(rootView.getResources());

        newCounterColor.setImageDrawable(ContextCompat.getDrawable(getRootView().getContext(), R.drawable.ic_check_24dp));
        switch (newCounterColor.getId()) {
            case new_counter_color_purple:
                newCounter.setColor(ContextCompat.getColor(newCounterColor.getContext(), R.color.colorPurple));
                break;
            case R.id.new_counter_color_green:
                newCounter.setColor(ContextCompat.getColor(newCounterColor.getContext(), R.color.colorGreen));
                break;
            case R.id.new_counter_color_red:
                newCounter.setColor(ContextCompat.getColor(newCounterColor.getContext(), R.color.colorRed));
                break;
            case R.id.new_counter_color_orange:
                newCounter.setColor(ContextCompat.getColor(newCounterColor.getContext(), R.color.colorOrange));
                break;
            case R.id.new_counter_color_yellow:
                newCounter.setColor(ContextCompat.getColor(newCounterColor.getContext(), R.color.colorYellow));
                break;
        }
    }

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

            presenter.saveACounter(newCounter);
        }
    }

    @Deprecated
    private OnClickListener newCounterEditText = new OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.new_counter_title_edit_text:
                    newCounter.setTitle(newCounterTitleEditText.getText().toString());
                    break;
                case R.id.new_counter_value_edit_text:
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

    @Override
    public void saveACounter(boolean saved) {
        if (saved) {
            Toast.makeText(rootView.getContext(), toastSavedWithSuccess, Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO something is wrong
//        Toast.makeText(rootView.getContext(), toastSavedWithSuccess, Toast.LENGTH_SHORT).show();
    }
}