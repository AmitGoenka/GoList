package org.agoenka.golist.models;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Author: agoenka
 * Created At: 9/29/2016
 * Version: ${VERSION}
 */

public class TodoWrapper {

    public static List<Todo> getTodoItems () {
        List<Todo> todoList = SQLite.select().from(Todo.class).orderBy(Todo_Table.createdDateTime, true).queryList();
        for (Todo todo : todoList) {
            todo.priority = Priority.get(todo.priorityCode);
        }
        return todoList;
    }

    public static Todo addTodoItems (String description, Date dueDate, Priority priority) {
        Todo todo = new Todo();

        Date createdDateTime = Calendar.getInstance().getTime();
        int priorityCode = priority != null ? priority.get() : 0;


        todo.createdDateTime = createdDateTime;
        todo.description = description.trim();
        if (dueDate != null)
            todo.dueDate = dueDate;
        todo.priorityCode = priorityCode;
        todo.priority = Priority.get(priorityCode);

        todo.save();
        return todo;
    }

    public static void saveTodoItems (Todo todo) {
        todo.save();
    }

    public static void deleteTodoItems(Todo todo) {
        todo.delete();
    }

}