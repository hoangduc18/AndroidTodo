package com.example.todo.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "todos")
public class TaskModel implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "task")
    private String taskName;
    private String note;
    private boolean done;
    private boolean favorite;
    private String reminder;
    @ColumnInfo(name = "due_date")
    private String dueDate;

    public TaskModel() {
    }

    public TaskModel(String taskName, String note, boolean done, boolean favorite, String reminder, String dueDate) {
        this.taskName = taskName;
        this.note = note;
        this.done = done;
        this.favorite = favorite;
        this.reminder = reminder;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
