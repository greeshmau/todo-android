package com.umapathi.greeshma.todoapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

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
        // Get the data item for this position
        ToDoItem item = (ToDoItem) getItem(position);
        // Check if an existing view is being rddeused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todotitle, parent, false);
        }
        // Lookup view for data population
        EditText etTitle = (EditText) convertView.findViewById(R.id.etTitle);
        //EditText etStatus = (EditText) convertView.findViewById(R.id.etStatus);
        //EditText etLevel = (EditText) convertView.findViewById(R.id.etLevel);

        /*Spinner spStatus = (Spinner) convertView.findViewById(R.id.spStatus);
        Spinner spLevel = (Spinner) convertView.findViewById(R.id.spLevel);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(statusAdapter);

        ArrayAdapter<CharSequence> levelAdapter = ArrayAdapter.createFromResource(this,
                R.array.level_array, android.R.layout.simple_spinner_item);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(levelAdapter);
*/
        etTitle.setText(item.Title);
        //etStatus.setText(item.Status);
        //etLevel.setText(item.Level);
        // Return the completed view to render on screen
        return convertView;

    }
}
