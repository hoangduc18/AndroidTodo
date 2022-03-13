package com.example.todo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.database.TaskModel;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder> {
    List<TaskModel> listTask;
    Context context;

    public TodoListAdapter(List<TaskModel> listTask, Context context) {
        this.listTask = listTask;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listTask.size();
    }

    public class TodoListViewHolder extends RecyclerView.ViewHolder{
        public TodoListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
