package com.example.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.adapter.TodoListAdapter;
import com.example.todo.database.AppDatabase;
import com.example.todo.database.TaskModel;
import com.example.todo.utils.CustomDialogUtil;
import com.example.todo.utils.DateConverter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IActionUpdateDateTime {

    Toolbar toolbar;
    TextView toolbarTitle;
    Button btnAdd;
    RecyclerView recyclerView;
    private TodoListAdapter adapter;
    private List<TaskModel> listTask;
    private AppDatabase db;
    TextView noTask;
    TextView todayTitle;
    TextView taskCount;

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
        todayTitle = findViewById(R.id.todayTitle);
        taskCount = findViewById(R.id.taskCount);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarTitle.setText("Todo List");

        todayTitle.setText("All");
        db = AppDatabase.getDatabase(this);
        listTask = new ArrayList<>();
        //listTask = db.taskDAO().getTasksByDate(DateConverter.getCurrentDate(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoListAdapter(new TodoListAdapter.IClickItem() {
            @Override
            public void updateTask(TaskModel taskModel) {
                clickItemToUpdate(taskModel);
            }

            @Override
            public void deleteTask(TaskModel taskModel) {
                clickItemToDelete(taskModel);
            }
        });
        recyclerView.setAdapter(adapter);


        btnAdd.setOnClickListener(view -> clickAddTask());

    }

    private void clickItemToDelete(TaskModel taskModel) {
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Are you sure to delete " + taskModel.getTaskName())
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    db.taskDAO().deleteTask(taskModel);
                    Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    loadData();
                    dialogInterface.dismiss();
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    private void clickItemToUpdate(TaskModel taskModel) {
        Intent intent = new Intent(this, EditTaskActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("task_object", taskModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void clickAddTask() {
        startActivity(new Intent(this, AddTaskActivity.class));
    }

    private void loadDataByDate(String date) {
        listTask.clear();
        listTask = db.taskDAO().getTasksByDate(date);
        if (listTask.size() > 0) {
            noTask.setVisibility(View.GONE);
            taskCount.setVisibility(View.VISIBLE);
            taskCount.setText(listTask.size() + " Task(s)");
            adapter.setData(listTask);
        } else {
            adapter.setData(listTask);
            taskCount.setVisibility(View.GONE);
            noTask.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No task on this date", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        listTask.clear();
        listTask = new ArrayList<>();
        listTask = db.taskDAO().getAllTask();
        if (listTask.size() > 0) {
            todayTitle.setText("All");
            taskCount.setVisibility(View.VISIBLE);
            taskCount.setText(listTask.size() + " Task(s)");
            noTask.setVisibility(View.GONE);
            adapter.setData(listTask);
        } else {
            adapter.setData(listTask);
            taskCount.setVisibility(View.GONE);
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
        if (newDate.equalsIgnoreCase(DateConverter.getCurrentDate(this))) {
            todayTitle.setText("Today");
        } else {
            todayTitle.setText(newDate);
        }
        loadDataByDate(newDate);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            loadData();
        }
    }

    @Override
    public void updatedTime(String newTime) {

    }
}