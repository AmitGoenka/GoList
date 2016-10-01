package org.agoenka.golist.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import org.agoenka.golist.R;
import org.agoenka.golist.models.Todo;
import org.agoenka.golist.models.TodoWrapper;
import org.agoenka.golist.adapters.TodoItemsAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Todo> items;
    private TodoItemsAdapter itemsAdapter;
    private ListView lvItems;

    private static final int REQUEST_CODE_EDIT = 1;

    public static final String TODO_ITEM_KEY = "todoItem";
    public static final String TODO_ITEM_POSITION_KEY = "todoItemPosition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = TodoWrapper.getTodoItems();
        itemsAdapter = new TodoItemsAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);

        setupListViewLongClickListener();
        setupListViewClickListener();
    }

    private void setupListViewLongClickListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                TodoWrapper.deleteTodoItems(items.get(position));
                                items.remove(position);
                                itemsAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, final int id) {
                                dialog.cancel();
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    private void setupListViewClickListener () {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editIntent = new Intent(getApplicationContext(), EditItemActivity.class);
                editIntent.putExtra(TODO_ITEM_KEY, items.get(position));
                editIntent.putExtra(TODO_ITEM_POSITION_KEY, position);
                startActivityForResult(editIntent, REQUEST_CODE_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_EDIT) {
            lvItems.requestFocus();
            Todo todoItem = (Todo) data.getSerializableExtra(TODO_ITEM_KEY);
            int position = data.getIntExtra(TODO_ITEM_POSITION_KEY, -1);
            if (position > -1) {
                items.set(position, todoItem);
                TodoWrapper.saveTodoItems(todoItem);
                itemsAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onAddItem(View view) {
        EditText eNewItem = (EditText) findViewById(R.id.etNewItem);
        Todo todo = TodoWrapper.addTodoItems(eNewItem.getText().toString(), null, null);
        itemsAdapter.add(todo);
        eNewItem.setText("");
        removeSoftInput(view);
    }

    private void removeSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}