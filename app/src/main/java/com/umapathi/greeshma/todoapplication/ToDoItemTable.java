package com.umapathi.greeshma.todoapplication;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Greeshma on 8/21/17.
 */

@Table(database = ToDoDatabase.class)
public class ToDoItemTable extends BaseModel {
    @Column
    @PrimaryKey
    public int id;

    @Column
    public String Title;

    @Column
    public String Status;

    @Column
    public String Level;

    @Column
    public String Date;

    public ToDoItemTable() {
    }

    public ToDoItemTable(String Title, String Status, String Level, String Date)
    {

        this.Title = Title;
        this.Status = Status;
        this.Level = Level;
        this.Date = Date;
        //this.Position = Position;
    }
}
