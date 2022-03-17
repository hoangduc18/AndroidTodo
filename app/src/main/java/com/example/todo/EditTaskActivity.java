package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.todo.database.AppDatabase;
import com.example.todo.database.TaskModel;
import com.example.todo.utils.CustomDialogUtil;

public class EditTaskActivity extends AppCompatActivity implements IActionUpdateDateTime {
    TextView toolbarTitle;
    Toolbar toolbar;
    EditText title;
    EditText date;
    EditText reminderTime;
    EditText note;
    CheckBox done;
    CheckBox favorite;
    Button btnSave;
    int taskId;
    private AppDatabase db;

    private TaskModel taskModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
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
        toolbarTitle.setText("Edit Task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());

        taskModel = (TaskModel) getIntent().getExtras().get("task_object");
        if (taskModel != null) {
            taskId = taskModel.getId();
            title.setText(taskModel.getTaskName());
            date.setText(taskModel.getDueDate());
            reminderTime.setText(taskModel.getReminder());
            note.setText(taskModel.getNote());
            done.setChecked(taskModel.isDone());
            favorite.setChecked(taskModel.isFavorite());
        }

        date.setOnClickListener(view -> chooseDate());

        reminderTime.setOnClickListener(view -> chooseReminderTime());

        btnSave.setOnClickListener(view -> updateTask());
    }

    private void chooseReminderTime() {
        CustomDialogUtil.showTimePickerDialog(this, this);
    }

    private void chooseDate() {
        CustomDialogUtil.showDatePickerDialog(this, this);
    }

    private void updateTask() {
        if (reminderTime.getText().toString().length() <= 0
                || date.getText().toString().length() <= 0
                || title.getText().toString().length() <= 0) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_LONG).show();
        } else {
            taskModel.setId(taskId);
            taskModel.setTaskName(title.getText().toString().trim());
            taskModel.setReminder(reminderTime.getText().toString());
            taskModel.setDueDate(date.getText().toString());
            taskModel.setNote(note.getText().toString().trim());
            taskModel.setDone(done.isChecked());
            taskModel.setFavorite(favorite.isChecked());
            db.taskDAO().updateTask(taskModel);
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            Intent intentResult = new Intent();
            setResult(RESULT_OK, intentResult);
            finish();
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