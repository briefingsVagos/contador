package com.tars.counter.view;

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
import com.tars.counter.presenter.EditCountActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tars.counter.R.id.edit_counter_value_edit_text;

/**
 * View class that inflates the edit_count_view
 * It was created to remove the view logic from Activities
 * that in this project should be treated as Presenters
 */
public class EditCountViewImpl implements MVP.NewCountView{

    private static PresenterEditCount presenter;
    private View rootView;
    Counter counterToEdit = new Counter("New counter", 0, 0);

    @BindString(R.string.error_name_isempty) String errorNameIsEmpty;
    @BindString(R.string.default_value) String defaultValue;
    @BindString(R.string.toast_counter_saved_with_success) String toastSavedWithSuccess;

    @BindView(R.id.edit_counter_title_edit_text) EditText editCounterTitleEditText;
    @BindView(edit_counter_value_edit_text) EditText editCounterValueEditText;

    public EditCountViewImpl(EditCountActivity context, ViewGroup container) {
        rootView = LayoutInflater.from(context).inflate(R.layout.edit_count_view, container);

        ButterKnife.bind(this, rootView);

        if (presenter == null)
            presenter = context;
    }

    @OnClick({ R.id.edit_counter_color_purple, R.id.edit_counter_color_green, R.id.edit_counter_color_red,
            R.id.edit_counter_color_orange, R.id.edit_counter_color_yellow})
    public void colorChose(View view) {
        switch (view.getId()) {
            case R.id.edit_counter_color_purple:
                counterToEdit.setColor(ContextCompat.getColor(view.getContext(), R.color.colorPurple));
                break;
            case R.id.edit_counter_color_green:
                counterToEdit.setColor(ContextCompat.getColor(view.getContext(), R.color.colorGreen));
                break;
            case R.id.edit_counter_color_red:
                counterToEdit.setColor(ContextCompat.getColor(view.getContext(), R.color.colorRed));
                break;
            case R.id.edit_counter_color_orange:
                counterToEdit.setColor(ContextCompat.getColor(view.getContext(), R.color.colorOrange));
                break;
            case R.id.edit_counter_color_yellow:
                counterToEdit.setColor(ContextCompat.getColor(view.getContext(), R.color.colorYellow));
                break;
        }
    }


    @OnClick(R.id.edit_counter_ok_button)
    public void submit(View view) {

        boolean valid = true;
        String finalName = editCounterTitleEditText.getText().toString();
        int finalValue = 0;

        if (finalName.trim().isEmpty()) {
            valid = false;
            editCounterTitleEditText.setError(EditCountViewImpl.this.errorNameIsEmpty);
        }
        else
            editCounterTitleEditText.setError(null);

        if (!editCounterValueEditText.getText().toString().trim().isEmpty())
            finalValue = Integer.valueOf(editCounterValueEditText.getText().toString());

        if (valid) {
            counterToEdit.setTitle(finalName);
            counterToEdit.setValue(finalValue);

            presenter.saveAEditedCounter(counterToEdit);
        }
    }

    @Deprecated
    private OnClickListener newCounterEditText = new OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.new_counter_title_edit_text:
                    counterToEdit.setTitle(editCounterTitleEditText.getText().toString());
                    break;
                case R.id.new_counter_value_edit_text:
                    String typeSafeNumber = editCounterValueEditText.getText().toString().trim();
                    counterToEdit.setValue(Integer.valueOf(typeSafeNumber.isEmpty()
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