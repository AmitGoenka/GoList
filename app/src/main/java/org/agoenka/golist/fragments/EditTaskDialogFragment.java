package org.agoenka.golist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import org.agoenka.golist.R;
import org.agoenka.golist.models.Priority;
import org.agoenka.golist.models.Todo;

import java.util.Calendar;

import static org.agoenka.golist.utils.DateUtils.getCalendar;
import static org.agoenka.golist.utils.DateUtils.getDate;

/**
 * Author: agoenka
 * Created At: 10/1/2016
 * Version: ${VERSION}
 */

public class EditTaskDialogFragment extends DialogFragment {

    public EditTaskDialogFragment () {}

    public static final String KEY_TITLE = "title";
    public static final String KEY_ITEM = "item";
    public static final String KEY_POSITION = "position";

    private Todo item;
    private int position;

    public interface EditTaskDialogListener {
        void onUpdate(Todo item, int position);
    }

    private EditTaskDialogListener listener;

    public void setEditTaskDialogListener (EditTaskDialogListener listener) {
        this.listener = listener;
    }

    public static EditTaskDialogFragment newInstance(String title, Todo item, int position) {
        EditTaskDialogFragment fragment = new EditTaskDialogFragment();
        fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_Dialog);
        fragment.setArguments(new Bundle());
        fragment.getArguments().putString(KEY_TITLE, title);
        fragment.getArguments().putSerializable(KEY_ITEM, item);
        fragment.getArguments().putInt(KEY_POSITION, position);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_item, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String title = getArguments().getString(KEY_TITLE);
        item = (Todo) getArguments().getSerializable(KEY_ITEM);
        position = getArguments().getInt(KEY_POSITION);

        getDialog().setTitle(title);
        setPrioritySpinnerAdapter(view);
        setViewElements(view);
        setSaveButtonClickListener(view);
    }

    private void setPrioritySpinnerAdapter(View view) {
        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(getContext(), R.array.priority_array, android.R.layout.simple_spinner_item);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner prioritySpinner = (Spinner) view.findViewById(R.id.spnPriority);
        prioritySpinner.setAdapter(priorityAdapter);
    }

    private void setViewElements(View view) {
        if (position > -1) {
            EditText etEditItem = (EditText) view.findViewById(R.id.etEditItem);
            DatePicker dpDueDate = (DatePicker) view.findViewById(R.id.dpDueDate);
            Spinner prioritySpinner = (Spinner) view.findViewById(R.id.spnPriority);

            etEditItem.setText(item.description);

            if (item.dueDate != null) {
                Calendar cal = getCalendar(item.dueDate);
                dpDueDate.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
            }

            if (item.priority != null) {
                ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) prioritySpinner.getAdapter();
                int spinnerPosition = adapter.getPosition(item.priority.getDescription());
                prioritySpinner.setSelection(spinnerPosition);
            }

            etEditItem.requestFocus(View.FOCUS_RIGHT);
        }
    }

    public void setSaveButtonClickListener(final View view) {
        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etEditItem = (EditText) view.findViewById(R.id.etEditItem);
                DatePicker dpDueDate = (DatePicker) view.findViewById(R.id.dpDueDate);
                Spinner prioritySpinner = (Spinner) view.findViewById(R.id.spnPriority);

                item.description = etEditItem.getText().toString().trim();
                item.dueDate = getDate(dpDueDate);
                item.priority = Priority.get(prioritySpinner.getSelectedItem().toString());

                if (listener != null) {
                    listener.onUpdate(item, position);
                }

                item = null;
                position = -1;
                dismiss();
            }
        });
    }

}
