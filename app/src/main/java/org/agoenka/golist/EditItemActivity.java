package org.agoenka.golist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import static org.agoenka.golist.DateUtils.getCalendar;
import static org.agoenka.golist.DateUtils.getDate;
import static org.agoenka.golist.MainActivity.TODO_ITEM_KEY;
import static org.agoenka.golist.MainActivity.TODO_ITEM_POSITION_KEY;

public class EditItemActivity extends AppCompatActivity {

    private Todo todoItem = null;
    private int todoItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        setPrioritySpinnerAdapter();

        Intent intent = getIntent();
        if (intent != null) {
            todoItem = (Todo) intent.getSerializableExtra(TODO_ITEM_KEY);
            todoItemPosition = intent.getIntExtra(TODO_ITEM_POSITION_KEY, -1);

            if (todoItemPosition > -1) {
                EditText etEditItem = (EditText) findViewById(R.id.etEditItem);
                DatePicker dpDueDate = (DatePicker) findViewById(R.id.dpDueDate);
                Spinner prioritySpinner = (Spinner) findViewById(R.id.spnPriority);

                etEditItem.setText(todoItem.description);

                if (todoItem.dueDate != null) {
                    Calendar cal = getCalendar(todoItem.dueDate);
                    dpDueDate.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
                }

                if (todoItem.priority != null) {
                    ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) prioritySpinner.getAdapter();
                    int spinnerPosition = adapter.getPosition(todoItem.priority.getDescription());
                    prioritySpinner.setSelection(spinnerPosition);
                }

                etEditItem.requestFocus(View.FOCUS_RIGHT);
            }
        }

    }

    public void onSave(View view) {
        EditText etEditItem = (EditText) findViewById(R.id.etEditItem);
        DatePicker dpDueDate = (DatePicker) findViewById(R.id.dpDueDate);
        Spinner prioritySpinner = (Spinner) findViewById(R.id.spnPriority);

        todoItem.description = etEditItem.getText().toString().trim();
        todoItem.dueDate = getDate(dpDueDate);
        todoItem.priority = Priority.get(prioritySpinner.getSelectedItem().toString());

        Intent data = new Intent();
        data.putExtra(TODO_ITEM_KEY, todoItem);
        data.putExtra(TODO_ITEM_POSITION_KEY, todoItemPosition);
        todoItem = null;
        todoItemPosition = -1;
        setResult(RESULT_OK, data);
        finish();
    }

    private void setPrioritySpinnerAdapter() {
        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(this, R.array.priority_array, android.R.layout.simple_spinner_item);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner prioritySpinner = (Spinner) findViewById(R.id.spnPriority);
        prioritySpinner.setAdapter(priorityAdapter);
    }

}