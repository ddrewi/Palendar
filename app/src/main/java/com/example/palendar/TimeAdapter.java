package com.example.palendar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;

import java.util.ArrayList;


//https://anna-scott.medium.com/clickable-listview-items-with-clickable-buttons-e52fa6030d36


public class TimeAdapter extends ArrayAdapter<Time> {

    public TimeAdapter(Context context, ArrayList<Time> timeList){
        super(context, 0, timeList);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Time myTime = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_toggle_button, parent, false);

        }

        ToggleButton timeToggleButton = (ToggleButton) convertView.findViewById(R.id.itemToggleButton);

        timeToggleButton.setText(myTime.getTime());
        timeToggleButton.setTextOff(myTime.getTime());
        timeToggleButton.setTextOn(myTime.getTime());

        // Return the completed view to render on screen
        return convertView;
    }
}
