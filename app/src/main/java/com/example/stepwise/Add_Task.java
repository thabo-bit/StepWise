package com.example.stepwise;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

/* loaded from: classes3.dex */
public class Add_Task extends AppCompatActivity {
    EditText Description;
    EditText date;
    RadioButton fifteen;
    RadioButton five;
    Button high;
    Button low;
    Button medium;
    RadioButton noReminder;
    RadioGroup notifications;
    RadioButton oneHour;
    String priority = null;
    String reminder = null;
    ArrayList<Task> taskArrayList = new ArrayList<>();
    EditText taskName;
    RadioButton thirty;
    EditText time;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_task);
        this.taskName = (EditText) findViewById(R.id.taskNameEditText);
        this.Description = (EditText) findViewById(R.id.taskDescriptionEditText);
        this.date = (EditText) findViewById(R.id.dateEditText);
        this.time = (EditText) findViewById(R.id.timeEditText);
        this.notifications = (RadioGroup) findViewById(R.id.reminderRadioGroup);
        this.medium = (Button) findViewById(R.id.mediumPriorityButton);
        this.low = (Button) findViewById(R.id.lowPriorityButton);
        this.high = (Button) findViewById(R.id.highPriorityButton);
        this.noReminder = (RadioButton) findViewById(R.id.reminderNone);
        this.five = (RadioButton) findViewById(R.id.reminder5min);
        this.fifteen = (RadioButton) findViewById(R.id.reminder15min);
        this.thirty = (RadioButton) findViewById(R.id.reminder30min);
        this.oneHour = (RadioButton) findViewById(R.id.reminder1hour);
        this.medium.setOnClickListener(new View.OnClickListener() { // from class: com.example.stepwise.Add_Task.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Add_Task.this.priority = "medium";
            }
        });
        this.low.setOnClickListener(new View.OnClickListener() { // from class: com.example.stepwise.Add_Task.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Add_Task.this.priority = "low";
            }
        });
        this.high.setOnClickListener(new View.OnClickListener() { // from class: com.example.stepwise.Add_Task.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Add_Task.this.priority = "high";
            }
        });
        if (this.noReminder.isChecked()) {
            Toast.makeText(this, "hello", 0).show();
        }
    }

    public void save_task(View view) {
        String taskname = this.taskName.getText().toString();
        String description = this.Description.getText().toString();
        String Date = this.date.getText().toString();
        String Time = this.time.getText().toString();
        String Newpriority = this.priority;
        if (this.noReminder.isChecked()) {
            this.reminder = "No reminder";
        } else if (this.five.isChecked()) {
            this.reminder = "5 minutes before";
        } else if (this.fifteen.isChecked()) {
            this.reminder = "15 minutes before";
        } else if (this.thirty.isChecked()) {
            this.reminder = "30 minutes before";
        } else if (this.oneHour.isChecked()) {
            this.reminder = "1 hour before";
        }
        if (taskname.isEmpty() || description.isEmpty() || Date.isEmpty() || (Time.isEmpty() && this.priority == null)) {
            this.taskName.setError("invalid");
            this.Description.setError("invalid");
            this.date.setError("invalid");
            this.time.setError("invalid");
            return;
        }
        DataBase db = new DataBase(this);
        db.Add_Task(Date, description, Time, taskname, this.reminder, Newpriority);
        this.taskName.setText(" ");
        this.Description.setText(" ");
        this.date.setText(" ");
        this.time.setText(" ");
    }

    public void choose_date(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(1);
        int month = calendar.get(2);
        int day = calendar.get(5);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() { // from class: com.example.stepwise.Add_Task.4
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public void onDateSet(DatePicker view2, int selectedYear, int selectedMonth, int selectedDay) {
                String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                Add_Task.this.date.setText(selectedDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void add_date(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(11);
        int minute = calendar.get(12);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() { // from class: com.example.stepwise.Add_Task.5
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public void onTimeSet(TimePicker view2, int selectedHour, int selectedMinute) {
                String formattedTime = String.format("%02d:%02d", Integer.valueOf(selectedHour), Integer.valueOf(selectedMinute));
                Add_Task.this.time.setText(formattedTime);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }
}