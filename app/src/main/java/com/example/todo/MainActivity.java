package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.todo.adapter.TodoListAdapter;
import com.example.todo.database.AppDatabase;
import com.example.todo.database.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarTitle;
    Button btnAdd;
    RecyclerView recyclerView;
    private TodoListAdapter adapter;
    private List<TaskModel> listTask;
    private AppDatabase db;
    TextView noTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recycler);
        /*noTask = findViewById(R.id.noTask);
        noTask.setVisibility(View.GONE);*/
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarTitle.setText("Todo List");

        db = AppDatabase.getDatabase(this);
        listTask = new ArrayList<>();
        listTask.clear();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoListAdapter(listTask,this);
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clickAddTask();
            }
        });

    }

    private void clickAddTask() {
        startActivity(new Intent(this,AddEditTaskActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }
}