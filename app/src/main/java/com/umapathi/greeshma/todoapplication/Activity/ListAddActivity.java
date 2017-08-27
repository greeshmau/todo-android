package com.umapathi.greeshma.todoapplication.Activity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.umapathi.greeshma.todoapplication.R;
import com.umapathi.greeshma.todoapplication.Model.ToDoItem;
import com.umapathi.greeshma.todoapplication.Model.ToDoItemTable;
import com.umapathi.greeshma.todoapplication.Adapter.TodoAdpater;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.umapathi.greeshma.todoapplication.Model.ToDoItemTable_Table.Date;
import static com.umapathi.greeshma.todoapplication.Model.ToDoItemTable_Table.Level;
import static com.umapathi.greeshma.todoapplication.Model.ToDoItemTable_Table.Status;
import static com.umapathi.greeshma.todoapplication.Model.ToDoItemTable_Table.Title;

public class ListAddActivity extends AppCompatActivity {
    private final int EDIT_CODE = 20;
    private final int ADD_CODE = 10;
    ArrayList<ToDoItem> arrayOfItems;
    TodoAdpater todoAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add);
        lvItems = (ListView) findViewById(R.id.lv_AllItems);
        arrayOfItems = new ArrayList<ToDoItem>();
        todoAdapter = new TodoAdpater(this, arrayOfItems);

        List<ToDoItemTable> allItems = SQLite.select()
                .from(ToDoItemTable.class)
                .queryList();
        for (ToDoItemTable item : allItems) {
            String priority = item.Level.equals("HIGH") ? "A" : (item.Level.equals("MEDIUM") ? "B" : "C");

            ToDoItem itemToAdd = new ToDoItem(item.Title, item.Status, item.Level, item.Date, priority);
            todoAdapter.add(itemToAdd);
        }
        lvItems.setAdapter(todoAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ////Toast.makeText(this, "Removing", Toast.LENGTH_SHORT).show();
                ToDoItem editItem = arrayOfItems.get(position);
                Intent i = new Intent(ListAddActivity.this, ListEditItemActivity.class);
                i.putExtra("EditingTitle", editItem.title);
                i.putExtra("EditingStatus", editItem.status);
                i.putExtra("EditingLevel", editItem.level);
                i.putExtra("EditingDate", editItem.date);
                i.putExtra("Action", "Edit");
                i.putExtra("Position", position);
                startActivityForResult(i, EDIT_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Toast.makeText(this, "coming back" + resultCode + requestCode, Toast.LENGTH_SHORT).show();
        if (resultCode == RESULT_OK && requestCode == EDIT_CODE) {
            String oldTitle = data.getExtras().getString("OldTitle");
            String oldLevel = data.getExtras().getString("OldLevel");
            String oldStatus = data.getExtras().getString("OldStatus");
            String oldDate = data.getExtras().getString("OldDate");

            String title = data.getExtras().getString("EditedTitle");
            String level = data.getExtras().getString("EditedLevel");
            String status = data.getExtras().getString("EditedStatus");
            String date = data.getExtras().getString("EditedDate");

            int position = data.getExtras().getInt("Position", 0);
            String priority = level.equals("HIGH") ? "A" : (level.equals("MEDIUM") ? "B" : "C");
            ToDoItem editedItem = new ToDoItem(title, status, level, date, priority);

            arrayOfItems.set(position, editedItem);
            todoAdapter.notifyDataSetChanged();
            //Toast.makeText(this, title + " " + title, Toast.LENGTH_SHORT).show();

            SQLite.update(ToDoItemTable.class)
                    .set(Title.eq(title),
                            Level.eq(level),
                            Status.eq(status),
                            Date.eq(date))
                    .where(Title.is(oldTitle))
                    //.and(ToDoItemTable_Table.title.is(OldTitle))
                    .async()
                    .execute(); // non-UI blocking

        }

        if (resultCode == RESULT_OK && requestCode == ADD_CODE) {

            String title = data.getExtras().getString("EditedTitle");
            String level = data.getExtras().getString("EditedLevel");
            String status = data.getExtras().getString("EditedStatus");
            String date = data.getExtras().getString("EditedDate");
            long dateInMillis = data.getExtras().getLong("DateInMillis", -1);

            //int position = data.getExtras().getInt("Position",0);

            Log.i("REGER", title + level + status + date);

            String priority = Level.equals("HIGH") ? "A" : (Level.equals("MEDIUM") ? "B" : "C");
            ToDoItem item2 = new ToDoItem(title, status, level, date, priority);
            arrayOfItems.add(item2);
            Log.i("REGER", item2.title + item2.level + item2.status + item2.date);
            //todoAdapter.add(item2);
            todoAdapter.notifyDataSetChanged();

            ToDoItemTable item2Tb = new ToDoItemTable(title, status, level, date);
            item2Tb.save();

            addAlarm(item2Tb, dateInMillis);

            //Toast.makeText(this, "Added" + " " + title, Toast.LENGTH_SHORT).show();
        }

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ToDoItem delItem = arrayOfItems.get(position);
                ////Toast.makeText(this, "Removing " + delItem.title, Toast.LENGTH_SHORT).show();

                SQLite.delete(ToDoItemTable.class)
                        .where(Title.eq(delItem.title))
                        .async()
                        .execute(); // non-UI blocking
                arrayOfItems.remove(position);
                todoAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void addAlarm(ToDoItemTable item2Tb, long dateInMillis) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 10);
        Log.i("GREES", String.valueOf(dateInMillis));
        //final long afterTenSeconds = dateInMillis;//= c.getTimeInMillis();


        final Intent myIntent = new Intent("com.umapathi.greeshma.todoapplication.alarm");
        myIntent.putExtra("title", item2Tb.Title);
        myIntent.putExtra("titleID", item2Tb.id);
        myIntent.setPackage(getPackageName());

        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), item2Tb.id,
                myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, dateInMillis, broadcast);

    }


    public void clickAddItem(View view) {
        ////Toast.makeText(this, "Removing", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ListAddActivity.this, ListEditItemActivity.class);
        i.putExtra("EditingTitle", "NEW");
        i.putExtra("EditingStatus", "NEW");
        i.putExtra("EditingLevel", "NEW");
        i.putExtra("Action", "NEW");
        //sendNotification(view);
        startActivityForResult(i, ADD_CODE);
    }

    public void sendNotification(View view) {


        android.support.v4.app.NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("My notification")
                .setContentText("Hello World!");

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //NotificationManager.notify()
        mNotificationManager.notify(001, mBuilder.build());
    }
}
