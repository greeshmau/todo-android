package com.umapathi.greeshma.todoapplication.Model;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Greeshma on 8/21/17.
 */

@Database(name = ToDoDatabase.NAME, version = ToDoDatabase.VERSION)
public class ToDoDatabase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}
