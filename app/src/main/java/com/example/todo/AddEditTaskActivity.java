package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.todo.database.AppDatabase;
import com.example.todo.database.TaskModel;
import com.example.todo.utils.CustomDialogUtil;

public class AddEditTaskActivity extends AppCompatActivity implements ActionUpdate {
    TextView toolbarTitle;
    Toolbar toolbar;
    EditText title;
    EditText date;
    EditText reminderTime;
    EditText note;
    CheckBox done;
    CheckBox favorite;
    Button btnSave;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        db = AppDatabase.getDatabase(this);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        reminderTime = findViewById(R.id.reminderTime);
        note = findViewById(R.id.note);
        done = findViewById(R.id.doneCheckBox);
        favorite = findViewById(R.id.favoriteCheckBox);
        btnSave = findViewById(R.id.btnSave);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarTitle.setText("Add New Task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                saveTask();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate();
            }
        });

        reminderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseReminderTime();
            }
        });


    }

    private void chooseReminderTime() {
        CustomDialogUtil.showTimePickerDialog(this, this);
    }

    private void chooseDate() {
        date.setText(CustomDialogUtil.selectedDate);
        CustomDialogUtil.showDatePickerDialog(this, this);

    }

    private void saveTask() {
        if (reminderTime.getText().toString().length() <= 0
                || date.getText().toString().length() <= 0
                || title.getText().toString().length() <= 0) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_LONG).show();
        } else {
            TaskModel taskModel = new TaskModel();
            taskModel.setTaskName(title.getText().toString());
            taskModel.setReminder(reminderTime.getText().toString());
            taskModel.setDueDate(date.getText().toString());
            if(note.getText().toString() != null || note.getText().toString() != ""){
                taskModel.setNote(note.getText().toString());
            }else{
                taskModel.setNote("");
            }

            if (done.isChecked()) {
                taskModel.setDone(true);
            } else {
                taskModel.setDone(false);
            }
            if (favorite.isChecked()) {
                taskModel.setFavorite(true);
            } else {
                taskModel.setFavorite(false);
            }
            db.taskDAO().insertTask(taskModel);
            title.setText("");
            reminderTime.setText("");
            date.setText("");
            note.setText("");
            done.setChecked(false);
            favorite.setChecked(false);
            Toast.makeText(this, "New task added", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void updatedDate(String newDate) {
        date.setText(newDate);
    }

    @Override
    public void updatedTime(String newTime) {
        reminderTime.setText(newTime);
    }

}

