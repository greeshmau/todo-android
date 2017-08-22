package com.umapathi.greeshma.todoapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ListEditItemActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etLevel;
    private Spinner spStatus;
    private Spinner spLevel;
    private int position;
    private TextView tvCalendar;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo);

        spStatus = (Spinner) findViewById(R.id.spStatus);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(statusAdapter);

        spLevel = (Spinner) findViewById(R.id.spLevel);
        ArrayAdapter<CharSequence> levelAdapter = ArrayAdapter.createFromResource(this,
                R.array.level_array, android.R.layout.simple_spinner_item);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLevel.setAdapter(levelAdapter);

        String ACTION = getIntent().getStringExtra("Action");
        if(ACTION.equals("Edit")) {
            Toast.makeText(this, "Editing ", Toast.LENGTH_SHORT).show();
            String EditingTitle = getIntent().getStringExtra("EditingTitle");
            String EditingStatus = getIntent().getStringExtra("EditingStatus");
            String EditingLevel = getIntent().getStringExtra("EditingLevel");
            String EditingDate = getIntent().getStringExtra("EditingDate");

            position = getIntent().getIntExtra("Position", 0);

            etTitle = (EditText) findViewById(R.id.etTitle);
            tvCalendar = (TextView) findViewById(R.id.tvCalendar);

            if (!EditingStatus.equals(null)) {
                int spinnerPosition = statusAdapter.getPosition(EditingStatus);
                spStatus.setSelection(spinnerPosition);
            }

            if (!EditingLevel.equals(null)) {
                int spinnerPosition = levelAdapter.getPosition(EditingLevel);
                spLevel.setSelection(spinnerPosition);
            }
            etTitle.setText(EditingTitle);
            tvCalendar.setText(EditingDate);
        }
        else if(ACTION.equals("New")) {
            Toast.makeText(this, "Adding ", Toast.LENGTH_SHORT).show();
            position = getIntent().getIntExtra("Position", 0);

            etTitle = (EditText) findViewById(R.id.etTitle);
            //etStatus = (EditText) findViewById(R.id.etStatus);
            //etLevel = (EditText) findViewById(R.id.etLevel);

            etTitle.setText("");
            tvCalendar.setText("Aug 30th, 2017");
            //etStatus.setText("");
            //etLevel.setText("");
        }

    }

    public void OnClickSave(View v) {

        Intent data = new Intent();

        etTitle = (EditText) findViewById(R.id.etTitle);
        //etStatus = (EditText) findViewById(R.id.etStatus);
        //etLevel = (EditText) findViewById(R.id.etLevel);
        spStatus=(Spinner) findViewById(R.id.spStatus);
        spLevel=(Spinner) findViewById(R.id.spLevel);

        String editedTitle = etTitle.getText().toString();
        String editedStatus = spStatus.getSelectedItem().toString();
        //String editedLevel = etLevel.getText().toString();
        String editedLevel = spLevel.getSelectedItem().toString();
        String editedDate = tvCalendar.getText().toString();

        Toast.makeText(this, "Finished editing " + editedTitle, Toast.LENGTH_SHORT).show();

        data.putExtra("EditedTitle", editedTitle);
        data.putExtra("EditedStatus", editedStatus);
        data.putExtra("EditedLevel", editedLevel);
        data.putExtra("EditedDate", editedDate);
        data.putExtra("Position", position);
        data.putExtra("code", 200);

        setResult(RESULT_OK, data);
        finish();
    }

    public void OnClickCalender(View view) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this,
                new mDateSetListener(), year, month, day);
        dialog.show();

    }

    class mDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int datePickerYear, int monthOfYear,
                              int dayOfMonth) {
            year = datePickerYear;
            month = monthOfYear;
            day = dayOfMonth;

            String dueDate = getStringForDate(year, month, day);
            tvCalendar.setText(dueDate);
        }
    }
    private String getStringForDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day);
        Date date = calendar.getTime();
        return new SimpleDateFormat("MMM", Locale.ENGLISH).format(date.getTime()) + " " + ordinal(Integer.parseInt(new SimpleDateFormat("dd", Locale.ENGLISH).format(date.getTime()))) + ", " + new SimpleDateFormat("yyyy", Locale.ENGLISH).format(date.getTime());
    }

    public static String ordinal(int i) {
        String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];

        }
    }
}
