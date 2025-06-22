package com.example.stepwise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class DataBase extends SQLiteOpenHelper {
    public static final String COLUMN_DATE = "DATE ";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_PRIORITY = "PRIORITY";
    public static final String COLUMN_REMINDER = "REMINDER";
    public static final String COLUMN_TASK_NAME = "TASK_NAME";
    public static final String COLUMN_TIME = "TIME";
    public static final String DATABASE_NAME = "Task.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Task_detailsE ";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) throws SQLException {
        db.execSQL(" CREATE TABLE  Task_detailsE ( COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, TASK_NAME  TEXT, DESCRIPTION  TEXT, PRIORITY  TEXT,DATE  TEXT, REMINDER TEXT, TIME TEXT )");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
        db.execSQL("DROP TABLE IF EXISTS Task_detailsE ");
    }

    public void Add_Task(String task, String description, String priority, String date, String reminder, String time) {
        SQLiteDatabase db = getWritableDatabase();
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
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Task_detailsE ", null);
        ArrayList<Task> task_holder = new ArrayList<>();
        if (cursor.moveToNext()) {
            do {
                task_holder.add(new Task(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        return task_holder;
    }
}