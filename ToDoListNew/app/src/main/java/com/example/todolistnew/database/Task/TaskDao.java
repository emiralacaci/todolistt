package com.example.todolistnew.database.Task;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todolistnew.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM Task")
    List<Task> getAllTasks();

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);
}
