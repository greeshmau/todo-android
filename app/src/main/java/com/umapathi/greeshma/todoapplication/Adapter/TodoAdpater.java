package com.umapathi.greeshma.todoapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.umapathi.greeshma.todoapplication.Model.ToDoItem;
import com.umapathi.greeshma.todoapplication.R;

import java.util.ArrayList;
import java.util.Comparator;

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

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

        TextView tvLevel = (TextView) convertView.findViewById(R.id.tvLevel);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);

        tvTitle.setText(item.title);
        tvLevel.setText(item.level);
        tvDate.setText(item.date);

        Log.i("REGER", item.title + item.level + item.status + item.date);
        if (item.level.equals("HIGH")) {
            tvLevel.setTextColor(Color.RED);
        } else if (item.level.equals("MEDIUM")) {
            tvLevel.setTextColor(Color.BLUE);
        } else if (item.level.equals("LOW")) {
            tvLevel.setTextColor(Color.GREEN);
        }

        if (item.status.equals("DONE")) {
            tvTitle.setPaintFlags(tvTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        } else {
            tvTitle.setPaintFlags(0);
        }
        //convertView.setInt(R.id.tvTitle, "setPaintFlags", Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        this.setNotifyOnChange(false);

        this.sort(new Comparator<ToDoItem>() {
            @Override
            public int compare(ToDoItem lhs, ToDoItem rhs) {
                return lhs.priority.compareTo(rhs.priority);
            }
        });

        this.setNotifyOnChange(true);
        super.notifyDataSetChanged();
    }
}
