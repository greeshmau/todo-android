package com.umapathi.greeshma.todoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ListEditItemActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etLevel;
    private Spinner spStatus;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo);
        spStatus = (Spinner) findViewById(R.id.spStatus);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(statusAdapter);


        String ACTION = getIntent().getStringExtra("Action");
        if(ACTION.equals("Edit")) {
            Toast.makeText(this, "Editing ", Toast.LENGTH_SHORT).show();
            String EditingTitle = getIntent().getStringExtra("EditingTitle");
            String EditingStatus = getIntent().getStringExtra("EditingStatus");
            String EditingLevel = getIntent().getStringExtra("EditingLevel");
            position = getIntent().getIntExtra("Position", 0);

            etTitle = (EditText) findViewById(R.id.etTitle);
            if (!EditingStatus.equals(null)) {
                int spinnerPosition = statusAdapter.getPosition(EditingStatus);
                spStatus.setSelection(spinnerPosition);
            }



            //etStatus = (EditText) findViewById(R.id.etStatus);
            etLevel = (EditText) findViewById(R.id.etLevel);

            etTitle.setText(EditingTitle);
            //etStatus.setText(EditingStatus);
            etLevel.setText(EditingLevel);
        }
        else if(ACTION.equals("New")) {
            Toast.makeText(this, "Adding ", Toast.LENGTH_SHORT).show();
            position = getIntent().getIntExtra("Position", 0);

            etTitle = (EditText) findViewById(R.id.etTitle);
            //etStatus = (EditText) findViewById(R.id.etStatus);
            etLevel = (EditText) findViewById(R.id.etLevel);

            etTitle.setText("");
            //etStatus.setText("");
            etLevel.setText("");
        }

    }

    public void OnClickSave(View v) {

        Intent data = new Intent();

        etTitle = (EditText) findViewById(R.id.etTitle);
        //etStatus = (EditText) findViewById(R.id.etStatus);
        etLevel = (EditText) findViewById(R.id.etLevel);
        spStatus=(Spinner) findViewById(R.id.spStatus);

        String editedTitle = etTitle.getText().toString();
        String editedStatus = spStatus.getSelectedItem().toString();
        String editedLevel = etLevel.getText().toString();

        Toast.makeText(this, "Finished editing " + editedTitle, Toast.LENGTH_SHORT).show();

        data.putExtra("EditedTitle", editedTitle);
        data.putExtra("EditedStatus", editedStatus);
        data.putExtra("EditedLevel", editedLevel);
        data.putExtra("Position", position);
        data.putExtra("code", 200);

        setResult(RESULT_OK, data);
        finish();
    }
}
