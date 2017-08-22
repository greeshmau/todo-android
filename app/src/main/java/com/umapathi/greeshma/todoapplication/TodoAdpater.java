package com.umapathi.greeshma.todoapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Greeshma on 8/20/17.
 */

public class TodoAdpater extends ArrayAdapter {
    public TodoAdpater(Context context, ArrayList<ToDoItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ToDoItem item = (ToDoItem) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todotitle, parent, false);
        }

        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);

        TextView tvLevel = (TextView)convertView.findViewById(R.id.tvLevel);

        tvTitle.setText(item.Title);
        tvLevel.setText(item.Level);

        return convertView;

    }
}
