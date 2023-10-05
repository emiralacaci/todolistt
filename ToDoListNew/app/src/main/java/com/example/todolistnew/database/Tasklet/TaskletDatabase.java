package com.example.todolistnew.database.Tasklet;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todolistnew.model.Tasklet;

@Database(entities = {Tasklet.class}, version = 1)
public abstract class TaskletDatabase extends RoomDatabase {
    public abstract TaskletDao taskletDao();
}
