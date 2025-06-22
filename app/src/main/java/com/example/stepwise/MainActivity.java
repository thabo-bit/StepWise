package com.example.stepwise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes3.dex */
public class MainActivity extends AppCompatActivity {
    Button nextPage;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void next(View view) {
        Intent intent = new Intent(this,  TaskManager.class);
        startActivity(intent);
    }
}