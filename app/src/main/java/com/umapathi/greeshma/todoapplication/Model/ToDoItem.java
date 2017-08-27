package com.umapathi.greeshma.todoapplication.Model;

/**
 * Created by Greeshma on 8/20/17.
 */

public class ToDoItem {
    public String title;
    public String status;
    public String level;
    public String date;
    public String priority;
    //public int Position;

    public ToDoItem(String title, String status, String level, String date, String priority) {
        this.title = title;
        this.status = status;
        this.level = level;
        this.date = date;
        this.priority = priority;
    }
}
