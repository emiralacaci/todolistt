package com.example.todolistnew.database.Task;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todolistnew.model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
