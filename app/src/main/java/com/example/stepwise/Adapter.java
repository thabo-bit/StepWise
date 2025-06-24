package com.example.stepwise;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    ArrayList<Task> myTask;


    public Adapter(Context context, ArrayList<Task> myTask) {
        this.context = context;
        this.myTask = myTask;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_looks, (ViewGroup) null, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.description.setText(this.myTask.get(position).getDescription());
        holder.taskname.setText(this.myTask.get(position).getTaskName());
        holder.priority.setText(this.myTask.get(position).getPriority());
        holder.time.setText(this.myTask.get(position).getTime());
        if (holder.priority.getText() == "medium") {
            holder.priority_color.setBackgroundColor(Color.rgb(251, 188, 5));
        }
        if (holder.priority.getText() == "high") {
            holder.priority_color.setBackgroundColor(Color.RED);
        }
        if (holder.priority.getText() == "low") {
            holder.priority_color.setBackgroundColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return this.myTask.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        TextView notification;
        TextView priority;
        View priority_color;
        TextView taskname;
        TextView time;

        public MyViewHolder(View itemView) {
            super(itemView);
            taskname = (TextView) itemView.findViewById(R.id.taskTitle);
            description = (TextView) itemView.findViewById(R.id.taskDescription);
            priority = (TextView) itemView.findViewById(R.id.taskPriority);
            time = (TextView) itemView.findViewById(R.id.taskDateTime);
            notification = (TextView) itemView.findViewById(R.id.taskNotificationTime);
            priority_color = itemView.findViewById(R.id.priorityIndicator);
        }
    }
}