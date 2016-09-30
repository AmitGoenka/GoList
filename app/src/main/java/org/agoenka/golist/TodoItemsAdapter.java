package org.agoenka.golist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static org.agoenka.golist.DateUtils.formatDate;
import static org.agoenka.golist.R.id.tvDueDate;
import static org.agoenka.golist.R.id.tvPriority;

/**
 * Author: agoenka
 * Created At: 9/29/2016
 * Version: ${VERSION}
 */

class TodoItemsAdapter extends ArrayAdapter<Todo> {

    private static class ViewHolder {
        TextView tvTodoDescription;
        TextView tvDueDate;
        TextView tvPriority;
    }

    TodoItemsAdapter(Context context, List<Todo> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Todo todo = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);

            viewHolder.tvTodoDescription = (TextView) convertView.findViewById(R.id.tvTodoDescription);
            viewHolder.tvDueDate = (TextView) convertView.findViewById(tvDueDate);
            viewHolder.tvPriority = (TextView) convertView.findViewById(tvPriority);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTodoDescription.setText(todo.description);
        viewHolder.tvDueDate.setText(formatDate(todo.dueDate));
        viewHolder.tvPriority.setText(todo.priority.getDescription());

        return convertView;
    }

}