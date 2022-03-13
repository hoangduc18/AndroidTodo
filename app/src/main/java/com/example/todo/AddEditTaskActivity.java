package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class AddEditTaskActivity extends AppCompatActivity {

    TextView toolbarTitle;
    Toolbar toolbar;
    EditText title;
    EditText date;
    EditText reminderTime;
    CheckBox done;
    CheckBox favorite;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        reminderTime = findViewById(R.id.reminderTime);
        done = findViewById(R.id.doneCheckBox);
        favorite = findViewById(R.id.favoriteCheckBox);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
    }
}