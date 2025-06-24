package com.example.stepwise;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskManager extends AppCompatActivity {

    RecyclerView Recycleview;
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<Task> mytask = new ArrayList<>();
    CalendarView calendarView;
    TextView name;
    String dateQuery;
    String taskName,time,reminder, priority,description,date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_manager);

        Recycleview = findViewById(R.id.tasksRecyclerView);
        calendarView = findViewById(R.id.calendarView);
        name = findViewById(R.id.name);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth + "/"  + month + "/" + year;

                name.setText(date);

                 dateQuery = "SELECT * FROM Task_detailsE WHERE DATE =  " + date;

            }
        });

        DataBase db = new DataBase(this);



        tasks = db.Task();
        for(int i =0 ; i < tasks.size(); i++){

            taskName = tasks.get(i).getTaskName();
            time = tasks.get(i).getTime();
            reminder = tasks.get(i).getReminder();
            priority = tasks.get(i).getPriority();
            description = tasks.get(i).getDescription();
            date = tasks.get(i).getDate();


            mytask.add(new Task(taskName,description,priority,date,reminder,time));

        }




        Adapter adapter = new Adapter(this,mytask);
        Recycleview.setAdapter(adapter);
        Recycleview.setLayoutManager(new LinearLayoutManager(this));


    }

    public void add_task(View view){
        Intent intent = new Intent(TaskManager.this, Add_Task.class);
        startActivity(intent);
    }
}
