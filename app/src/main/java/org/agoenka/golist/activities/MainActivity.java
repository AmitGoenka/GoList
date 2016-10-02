package org.agoenka.golist.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import org.agoenka.golist.R;
import org.agoenka.golist.adapters.TodoItemsAdapter;
import org.agoenka.golist.fragments.DeleteTaskDialogFragment;
import org.agoenka.golist.fragments.EditTaskDialogFragment;
import org.agoenka.golist.models.Todo;
import org.agoenka.golist.models.TodoWrapper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Todo> items;
    private TodoItemsAdapter itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = TodoWrapper.getTodoItems();
        itemsAdapter = new TodoItemsAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);

        setListViewLongClickListener();
        setListViewClickListener();
    }

    private void setListViewLongClickListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                DeleteTaskDialogFragment deleteDialog = DeleteTaskDialogFragment.newInstance("Delete Task", position);
                deleteDialog.setDeleteTaskDialogListener(new DeleteTaskDialogFragment.DeleteTaskDialogListener() {
                    @Override
                    public void onDelete(int position) {
                        TodoWrapper.deleteTodoItems(items.get(position));
                        items.remove(position);
                        itemsAdapter.notifyDataSetChanged();
                    }
                });
                FragmentManager fragmentManager = getSupportFragmentManager();
                deleteDialog.show(fragmentManager, "DeleteTask");
                return true;
            }
        });
    }

    private void setListViewClickListener() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditTaskDialogFragment editDialog = EditTaskDialogFragment.newInstance("Edit Task", items.get(position), position);
                editDialog.setEditTaskDialogListener(new EditTaskDialogFragment.EditTaskDialogListener() {
                    @Override
                    public void onUpdate(Todo item, int position) {
                        if (position > -1) {
                            items.set(position, item);
                            TodoWrapper.saveTodoItems(item);
                            itemsAdapter.notifyDataSetChanged();
                        }
                        lvItems.requestFocus();
                    }
                });
                FragmentManager fragmentManager = getSupportFragmentManager();
                editDialog.show(fragmentManager, "EditTask");
            }
        });
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