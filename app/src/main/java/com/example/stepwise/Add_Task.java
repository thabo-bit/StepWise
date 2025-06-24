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
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_task);

        taskName = findViewById(R.id.taskNameEditText);
        Description = findViewById(R.id.taskDescriptionEditText);
        date = findViewById(R.id.dateEditText);
        time = findViewById(R.id.timeEditText);
        notifications = findViewById(R.id.reminderRadioGroup);
        medium = findViewById(R.id.mediumPriorityButton);
        low = findViewById(R.id.lowPriorityButton);
        high = findViewById(R.id.highPriorityButton);
        noReminder = findViewById(R.id.reminderNone);
        five = findViewById(R.id.reminder5min);
        fifteen = findViewById(R.id.reminder15min);
        thirty = findViewById(R.id.reminder30min);
        oneHour = findViewById(R.id.reminder1hour);

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority = "medium";
            }
        });

        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority = "low";
            }
        });

        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority = "high";
            }
        });

        if (noReminder.isChecked()) {
           // Toast.makeText(this, "hello").show();
        }
    }

    public void save_task(View view) {
        String taskname = taskName.getText().toString();
        String description = Description.getText().toString();
        String Date = date.getText().toString();
        String Time = time.getText().toString();
        String Newpriority = priority;

        if (noReminder.isChecked()) {
            reminder = "No reminder";
        } else if (five.isChecked()) {
            reminder = "5 minutes before";
        } else if (fifteen.isChecked()) {
            reminder = "15 minutes before";
        } else if (thirty.isChecked()) {
            reminder = "30 minutes before";
        } else if (oneHour.isChecked()) {
            reminder = "1 hour before";
        }

        if (taskname.isEmpty() || description.isEmpty() || Date.isEmpty() || (Time.isEmpty() && priority == null)) {
            taskName.setError("invalid");
            Description.setError("invalid");
            date.setError("invalid");
            time.setError("invalid");
            return;
        }

        DataBase db = new DataBase(this);
        db.Add_Task(taskname, description, Newpriority, Date, reminder, Time);

        taskName.setText(" ");
        Description.setText(" ");
        date.setText(" ");
        time.setText(" ");
    }

    public void choose_date(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view2, int selectedYear, int selectedMonth, int selectedDay) {
                String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                date.setText(selectedDate);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(Add_Task.this, "channel_id")
                        .setSmallIcon(R.drawable.notifications)
                        .setContentTitle("New Task")
                        .setContentText("Click to open your task list");
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void add_date(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view2, int selectedHour, int selectedMinute) {
                String formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                time.setText(formattedTime);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }
}
