package com.example.todo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {
    @Insert
    public void insertTask(TaskModel taskModel);

    @Query("SELECT * FROM todos WHERE due_date == :date")
    public List<TaskModel> getTasksByDate(String date);

    @Query("SELECT * FROM todos")
    List<TaskModel> getAllTask();
}
