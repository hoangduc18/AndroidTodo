package com.example.todo.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDAO {
    @Insert
    void insertTask(TaskModel taskModel);

    @Query("SELECT * FROM todos WHERE due_date == :date")
    List<TaskModel> getTasksByDate(String date);

    @Query("SELECT * FROM todos")
    List<TaskModel> getAllTask();

    @Update
    void updateTask(TaskModel taskModel);

    @Delete
    void deleteTask(TaskModel taskModel);
}
