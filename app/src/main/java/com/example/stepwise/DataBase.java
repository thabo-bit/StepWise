package com.example.stepwise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_PRIORITY = "PRIORITY";
    public static final String COLUMN_REMINDER = "REMINDER";
    public static final String COLUMN_TASK_NAME = "TASK_NAME";
    public static final String COLUMN_TIME = "TIME";
    public static final String TABLE_NAME = "Task_detailsE";
    public static final String DATABASE_NAME = "Task.db";
    public static final int DATABASE_VERSION = 1;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PRIORITY + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_REMINDER + " TEXT, " +
                COLUMN_TIME + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db); // Recreate the table after dropping
    }

    public void Add_Task(String task, String description, String priority, String date, String reminder, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TASK_NAME, task);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_PRIORITY, priority);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_REMINDER, reminder);
        cv.put(COLUMN_TIME, time);
        db.insert(TABLE_NAME, null, cv);
    }

    public ArrayList<Task> Task() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Task_detailsE", null);
        ArrayList<Task> task_holder = new ArrayList<>();

        while (cursor.moveToNext()) {
            task_holder.add(new Task(
                    cursor.getString(1), // Task Name
                    cursor.getString(2), // Description
                    cursor.getString(3), // Priority
                    cursor.getString(4), // Date
                    cursor.getString(5), // Reminder
                    cursor.getString(6)  // Time
            ));
        }

        cursor.close();
        return task_holder;
    }
}
