package com.example.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_items, parent, false);
        return new TodoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, int position) {
        holder.taskName.setText(listTask.get(position).getTaskName());
        holder.reminderTime.setText(listTask.get(position).getReminder());
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if(listTask != null){
            return listTask.size();
        }
        return 0;
    }

    public class TodoListViewHolder extends RecyclerView.ViewHolder{
        private TextView taskName ;
        private TextView reminderTime;
        private ImageView more;

        public TodoListViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            reminderTime = itemView.findViewById(R.id.time);
            more = itemView.findViewById(R.id.more);
        }
    }
}
