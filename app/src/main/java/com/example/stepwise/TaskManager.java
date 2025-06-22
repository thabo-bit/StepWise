package com.example.stepwise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskManager extends AppCompatActivity {

    RecyclerView Recycleview;
    ArrayList<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_manager);

        Recycleview = findViewById(R.id.tasksRecyclerView);

        DataBase db = new DataBase(this);

        tasks = db.Task();

        Adapter adapter = new Adapter(this,tasks);
        Recycleview.setAdapter(adapter);
        Recycleview.setLayoutManager(new LinearLayoutManager(this));


    }

    public void add_task(View view){
        Intent intent = new Intent(TaskManager.this, Add_Task.class);
        startActivity(intent);
    }
}
