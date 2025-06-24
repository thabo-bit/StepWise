package com.example.stepwise;

/* loaded from: classes3.dex */
public class Task {
    String date;
    String description;
    String priority;
    String reminder;
    String taskName;
    String time;

    public Task(String taskName, String description, String priority, String date, String reminder, String time) {
        this.taskName = taskName;
        this.description = description;
        this.priority = priority;
        this.date = date;
        this.reminder = reminder;
        this.time = time;
    }


    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getReminder() {
        return this.reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}