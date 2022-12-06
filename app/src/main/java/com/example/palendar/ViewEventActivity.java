package com.example.palendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
    }

    public void returnHome (View view) {
        Intent intent = new Intent(ViewEventActivity.this, HomeActivity.class);
        startActivity(intent);
    }



}