package com.example.todolistnew.database.Tasklet;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolistnew.model.Tasklet;

import java.util.List;

@Dao
public interface TaskletDao {
    @Query("SELECT * FROM Tasklet")
    List<Tasklet> getAllTasklets();

    @Query("SELECT * FROM Tasklet WHERE task_id = :taskId")
    List<Tasklet> getTasklet(int taskId);

    @Query("UPDATE Tasklet SET done = :isDone WHERE uid = :uid")
    void update(int uid, boolean isDone);

    @Insert
    void insert(Tasklet... tasklets);

    @Delete
    void delete(Tasklet tasklet);


}
