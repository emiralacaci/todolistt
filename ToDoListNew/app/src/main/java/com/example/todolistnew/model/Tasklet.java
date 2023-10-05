package com.example.todolistnew.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tasklet {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "done")
    private boolean isDone;

    @ColumnInfo(name = "task_id")
    private int taskId;

    public Tasklet(String description, int taskId) {
        this.description = description;
        this.taskId=taskId;
        isDone=false;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
