package com.tars.counter.view;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tars.counter.R;
import com.tars.counter.contract.MVP;
import com.tars.counter.model.Counter;
import com.tars.counter.presenter.EditCounterActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

import static com.tars.counter.R.id.counter_color_purple;
import static com.tars.counter.R.id.counter_value_edit_text;

/**
 * View class that inflates the edit_view
 * It was created to remove the view logic from Activities
 * that in this project should be treated as Presenters
 */
public class EditViewImpl implements MVP.EditView {

    private MVP.PresenterEditCounter presenter;
    private View rootView;
    Counter counterToEdit;

    @BindString(R.string.toast_counter_saved_with_success) String toastSavedWithSuccess;

    @BindView(R.id.counter_title_edit_text) EditText counterTitleEditText;
    @BindView(counter_value_edit_text) EditText counterValueEditText;

    public EditViewImpl(EditCounterActivity context, ViewGroup container, Counter counterToEdit) {
        rootView = LayoutInflater.from(context).inflate(R.layout.counter_view, container);
        ButterKnife.bind(this, rootView);

        presenter = context;
        this.counterToEdit = counterToEdit;
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

    private void fillCounterToEditInScreen(FloatingActionButton counterToEditColor) {
        ButterKnife.apply(colors, UNSELECT_ALL);
        List<Resources> viewResources = new ArrayList<>();
        viewResources.add(rootView.getResources());

        counterToEditColor.setImageDrawable(ContextCompat.getDrawable(getRootView().getContext(), R.drawable.ic_check_24dp));

        switch (counterToEdit.getColor()) {
            case counter_color_purple:
                counterToEdit.setColor(ContextCompat.getColor(counterToEditColor.getContext(), R.color.colorPurple));
                break;
            case R.id.counter_color_green:
                counterToEdit.setColor(ContextCompat.getColor(counterToEditColor.getContext(), R.color.colorGreen));
                break;
            case R.id.counter_color_red:
                counterToEdit.setColor(ContextCompat.getColor(counterToEditColor.getContext(), R.color.colorRed));
                break;
            case R.id.counter_color_orange:
                counterToEdit.setColor(ContextCompat.getColor(counterToEditColor.getContext(), R.color.colorOrange));
                break;
            case R.id.counter_color_yellow:
                counterToEdit.setColor(ContextCompat.getColor(counterToEditColor.getContext(), R.color.colorYellow));
                break;
        }
    }

    @Override
    public View getRootView() {
        return rootView;
    }
}
