package com.example.todo.database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TaskDAO {
    @Insert
    public void insertTask(TaskModel taskModel);
}
