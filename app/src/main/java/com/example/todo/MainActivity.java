package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo.adapter.TodoListAdapter;
import com.example.todo.database.AppDatabase;
import com.example.todo.database.TaskModel;
import com.example.todo.utils.CustomDialogUtil;
import com.example.todo.utils.DateConverter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActionUpdate{

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
        noTask = findViewById(R.id.noTask);
        noTask.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarTitle.setText("Todo List");

        db = AppDatabase.getDatabase(this);
        listTask = new ArrayList<>();
        //listTask = db.taskDAO().getTasksByDate(DateConverter.getCurrentDate(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoListAdapter();
        recyclerView.setAdapter(adapter);
        //loadData();
        //adapter.setData(listTask);



        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clickAddTask();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void clickAddTask() {
        startActivity(new Intent(this,AddEditTaskActivity.class));
    }

    private void loadDataByDate(String date){
        listTask = new ArrayList<>();
        listTask = db.taskDAO().getTasksByDate(date);
        if (listTask.size() > 0){
            noTask.setVisibility(View.GONE);
            adapter.setData(listTask);
        }else {
            adapter.setData(listTask);
            noTask.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No task on this date", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData(){
        listTask = new ArrayList<>();
        listTask = db.taskDAO().getAllTask();
        if (listTask.size() > 0){
            noTask.setVisibility(View.GONE);
            adapter.setData(listTask);
        }else {
            adapter.setData(listTask);
            noTask.setVisibility(View.VISIBLE);
            Toast.makeText(this, "There is no task left", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CustomDialogUtil.showDatePickerDialog(this, this);
        return true;
    }

    @Override
    public void updatedDate(String newDate) {
        loadDataByDate(newDate);
        //Toast.makeText(this, "loaded"+ newDate, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updatedTime(String newTime) {

    }
}