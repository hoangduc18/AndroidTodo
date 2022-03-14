package com.example.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.database.TaskModel;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder> {
    List<TaskModel> listTask;
    Context context;
    private  IClickItem iClickItem;

    public interface IClickItem{
        void updateTask(TaskModel taskModel);

        void deleteTask(TaskModel taskModel);
    }

    public TodoListAdapter(IClickItem iClickItem) {
        this.iClickItem = iClickItem;
    }

    public TodoListAdapter() {
    }

    public void setData(List<TaskModel> listTask){
        this.listTask = listTask;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_items, parent, false);
        return new TodoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, int position) {
        TaskModel taskModel = listTask.get(position);
        holder.taskName.setText(listTask.get(position).getTaskName());
        holder.reminderTime.setText(listTask.get(position).getReminder());
        if (listTask.get(position).isDone()){
            holder.taskName.setText(listTask.get(position).getTaskName() + " (Done)");
            holder.taskBackground.setBackgroundResource(R.color.gray);
        }else{
            holder.taskBackground.setBackgroundResource(0);
        }
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItem.updateTask(taskModel);

            }
        });

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItem.deleteTask(taskModel);
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
        private ImageView deletebtn;
        private ImageView editBtn;
        private RelativeLayout taskBackground;

        public TodoListViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            reminderTime = itemView.findViewById(R.id.time);
            deletebtn = itemView.findViewById(R.id.deleteBtn);
            editBtn = itemView.findViewById(R.id.editBtn);
            deletebtn = itemView.findViewById(R.id.deleteBtn);
            taskBackground = itemView.findViewById(R.id.background);
        }
    }
}
