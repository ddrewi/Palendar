package com.example.palendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewEventActivity extends AppCompatActivity {

    private static final String EVENT_VALUE = "DATA TO DISPLAY ON NEXT PAGE";
    Event eventToDisplay;
    TextView codeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        Intent intent = getIntent();
        eventToDisplay = intent.getParcelableExtra(FillScheduleActivity.EVENT_VALUE);
        codeTextView = findViewById(R.id.codeTextView);

        codeTextView.setText("Join Code: " + HomeActivity.firebaseHelper.getDocID());

        GroupTimeAdapter groupTimeAdapter = new GroupTimeAdapter(this, eventToDisplay.getTimes());

        GridView gridView = findViewById(R.id.preferences);

        gridView.setAdapter(groupTimeAdapter);
    }


    public void returnHome (View view) {
        Intent intent = new Intent(ViewEventActivity.this, HomeActivity.class);
        startActivity(intent);
    }



}