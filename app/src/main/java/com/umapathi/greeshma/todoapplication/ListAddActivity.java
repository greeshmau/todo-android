package com.umapathi.greeshma.todoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAddActivity extends AppCompatActivity {
    ArrayList<ToDoItem> arrayOfItems;
    TodoAdpater todoAdapter;
    private ListView lvItems;
    private EditText et_NewItem;
    private final int EDIT_CODE = 20;
    private final int ADD_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add);

        //LinearLayout ll = (LinearLayout) findViewById(R.id.lv_AllItems);
        //TextView tv = (TextView) ll.findViewById(R.id.tvStatus); // get the child text view
        //final String text = tv.getText().toString();

        lvItems = (ListView) findViewById(R.id.lv_AllItems);
        //et_NewItem = (EditText) findViewById(R.id.et_NewItem);
        arrayOfItems = new ArrayList<ToDoItem>();
        todoAdapter  = new TodoAdpater(this, arrayOfItems);

        ToDoItem item = new ToDoItem("Buy Chocolate", "TBD", "HIGH");
        todoAdapter.add(item);
        ToDoItem item2 = new ToDoItem("Buy Chocolate2", "TBD", "MEDIUM");
        todoAdapter.add(item2);
        lvItems.setAdapter(todoAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(this, "Removing", Toast.LENGTH_SHORT).show();
                ToDoItem editItem = arrayOfItems.get(position);
                Intent i = new Intent(ListAddActivity.this, ListEditItemActivity.class);
                i.putExtra("EditingTitle",editItem.Title);
                i.putExtra("EditingStatus",editItem.Status);
                i.putExtra("EditingLevel",editItem.Level);
                i.putExtra("Action","Edit");
                i.putExtra("Position", position);
                startActivityForResult(i, EDIT_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(this, "coming back" + resultCode + requestCode, Toast.LENGTH_SHORT).show();
        if (resultCode == RESULT_OK && requestCode == EDIT_CODE) {
            String Title = data.getExtras().getString("EditedTitle");
            String Level = data.getExtras().getString("EditedLevel");
            String Status = data.getExtras().getString("EditedStatus");
            int position = data.getExtras().getInt("Position",0);

            ToDoItem editedItem = new ToDoItem(Title,Status,Level);
            String EditedItem = data.getExtras().getString("EditedItem");
            arrayOfItems.set(position,editedItem);
            todoAdapter.notifyDataSetChanged();
            Toast.makeText(this, EditedItem + " " + Title, Toast.LENGTH_SHORT).show();
        }

        if (resultCode == RESULT_OK && requestCode == ADD_CODE) {
            String Title = data.getExtras().getString("EditedTitle");
            String Level = data.getExtras().getString("EditedLevel");
            String Status = data.getExtras().getString("EditedStatus");
            //int position = data.getExtras().getInt("Position",0);


            ToDoItem item2 = new ToDoItem(Title, Level, Status);
            arrayOfItems.add(item2);
            //todoAdapter.add(item2);
            todoAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Added" + " " + Title, Toast.LENGTH_SHORT).show();
        }

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ToDoItem delItem = arrayOfItems.get(position);
                //Toast.makeText(this, "Removing " + delItem.Title, Toast.LENGTH_SHORT).show();
                arrayOfItems.remove(position);
                todoAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void clickAddItem(View veiw)
    {
        //Toast.makeText(this, "Removing", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ListAddActivity.this, ListEditItemActivity.class);
        i.putExtra("EditingTitle","NEW");
        i.putExtra("EditingStatus","NEW");
        i.putExtra("EditingLevel","NEW");
        i.putExtra("Action","NEW");
        startActivityForResult(i, ADD_CODE);
    }


    /*private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private EditText et_NewItem;
    private final int LISTITEMACT_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add);
        lvItems = (ListView) findViewById(R.id.lv_AllItems);
        et_NewItem = (EditText) findViewById(R.id.et_NewItem);
        items = new ArrayList<>();

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String remItem = items.get(position);
                //Toast.makeText(this, "Removing " + remItem, Toast.LENGTH_SHORT).show();
                itemsAdapter.remove(remItem);
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String remItem = items.get(position);
                Intent i = new Intent(ListAddActivity.this, ListEditItemActivity.class);
                i.putExtra("EditingItem",remItem);
                i.putExtra("Position", position);
                startActivityForResult(i, LISTITEMACT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Toast.makeText(this, "coming back" + resultCode + requestCode, Toast.LENGTH_SHORT).show();
        if (resultCode == RESULT_OK && requestCode == LISTITEMACT_CODE) {
            String EditedItem = data.getExtras().getString("EditedItem");
            int position = data.getExtras().getInt("Position",0);
            items.set(position,EditedItem);
            itemsAdapter.notifyDataSetChanged();
            Toast.makeText(this, EditedItem + " " + position, Toast.LENGTH_SHORT).show();
        }
    }

    public void clickAddItem(View veiw)
    {
        String fieldValue = et_NewItem.getText().toString();
        if(!fieldValue.isEmpty()) {
            itemsAdapter.add(fieldValue);
            et_NewItem.setText("");
            Toast.makeText(this, "Adding " + fieldValue, Toast.LENGTH_SHORT).show();
        }
    }*/
}
