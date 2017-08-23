package com.umapathi.greeshma.todoapplication;

/**
 * Created by Greeshma on 8/20/17.
 */

public class ToDoItem {
    public String Title;
    public String Status;
    public String Level;
    public String Date;
    public String priority;
    //public int Position;

    public ToDoItem(String Title, String Status, String Level, String Date, String priority)
    {
        this.Title = Title;
        this.Status = Status;
        this.Level = Level;
        this.Date = Date;
        this.priority = priority;
    }
}
