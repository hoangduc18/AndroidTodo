package com.example.todo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TaskModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDAO taskDAO();

    public static AppDatabase INSTANCE_DB;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE_DB == null) {
            INSTANCE_DB = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "TodoApp")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE_DB;
    }
}
