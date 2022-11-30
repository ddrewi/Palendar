package com.example.palendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CreateEventActivity extends AppCompatActivity {

    public static final String ARRAYLIST_VALUES = "Data to display in gridView";
    private static final String TAG = "ARIAGNO";
    public static FirebaseHelper firebaseHelper;

    EditText eventNameET;
    Spinner spinner1;
    Spinner spinner2;
    CalendarView calendarView;
    String todayDate;
    String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        firebaseHelper = new FirebaseHelper();

        eventNameET = findViewById(R.id.eventNameET);
        spinner1 = findViewById(R.id.time1Spinner);
        spinner2 = findViewById(R.id.time2Spinner);
        calendarView = findViewById(R.id.calendarView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_custom_spinner_dropdown, getResources().getStringArray(R.array.times));

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String selectedDate = "" + dayOfMonth + "/" + month + "/" + year;
                Log.d(TAG, "New date Selected: " + selectedDate);
                date = selectedDate;
            }
        });


    }

    public ArrayList<String> getTimes(){
        int firstTimeIndex = 0;
        int secondTimeIndex = 0;
        Spinner t1Spinner = findViewById(R.id.time1Spinner);
        Spinner t2Spinner = findViewById(R.id.time2Spinner);

        String t1SpinnerText = t1Spinner.getSelectedItem().toString();
        String t2SpinnerText = t2Spinner.getSelectedItem().toString();

        ArrayList<String> timesToDisplay = new ArrayList<>();
        String[] timeStrings = getResources().getStringArray(R.array.times);

        for(int i = 0; i < timeStrings.length; i++){
            if(t1SpinnerText.equals(timeStrings[i])){
                firstTimeIndex = i;
            } else if(t2SpinnerText.equals(timeStrings[i])){
                secondTimeIndex = i;
            }
        }

        if(firstTimeIndex <= secondTimeIndex){
            for(int i = firstTimeIndex; i < secondTimeIndex; i++){
                timesToDisplay.add(timeStrings[i]);
            }
        }else{
            for(int i = firstTimeIndex; i < timeStrings.length; i++){
                timesToDisplay.add(timeStrings[i]);
            }
            for(int i = 0; i < secondTimeIndex; i++){
                timesToDisplay.add(timeStrings[i]);
            }
        }

        return timesToDisplay;
    }

    public void createEvent(View view){
        String eventName = eventNameET.toString();
        String time1 = spinner1.toString();
        String time2 = spinner2.toString();

        ArrayList<String> timesToDisplay = getTimes();

        ArrayList<Time> timeList = new ArrayList<>();

        for(int i = 0; i < timesToDisplay.size(); i++){
            timeList.add(new Time(timesToDisplay.get(i)));
        }


        Event event = new Event(eventName, time1, time2, date, timeList);
        Log.d(TAG, "Event Created" + date);


        firebaseHelper.addEventToFirestore(event);


        Intent intent = new Intent(CreateEventActivity.this, FillScheduleActivity.class);
        //intent.putParcelableArrayListExtra(ARRAYLIST_VALUES, timeList);
        intent.putExtra(ARRAYLIST_VALUES, event);
        startActivity(intent);
    }

//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//    }
//    // This method is required, even if empty, for the OnItemSelectedListener to work
//
//    public void onNothingSelected(AdapterView<?> parent) { }
}