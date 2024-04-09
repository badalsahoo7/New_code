package com.example.vivify_technocrats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class NewActivity extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
       // button = findViewById(R.id.button);
        // this code helps us to going to the dashboard activity after some seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent = new Intent(NewActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        },1500);


    }
}