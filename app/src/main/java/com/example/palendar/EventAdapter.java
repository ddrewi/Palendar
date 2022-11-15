package com.example.palendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Context context, ArrayList<Event> eventList){
        super(context, 0, eventList);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Event myEvent = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_toggle_button, parent, false);

        }
        // Lookup view for data population
//        TextView foodNameTV = (TextView) convertView.findViewById(R.id.foodName);
//        TextView foodPriceTV = (TextView) convertView.findViewById(R.id.priceTV);
//        TextView foodDescTV = (TextView) convertView.findViewById(R.id.descriptionTV);

        ToggleButton timeToggleButton = (ToggleButton) convertView.findViewById(R.id.itemToggleButton);

        // Populate the data into the template view using the data object
//        foodNameTV.setText(myFood.getName());
//        foodPriceTV.setText("$" + myFood.getPrice());
//        foodDescTV.setText(myFood.getDesc());

        timeToggleButton.setTextOn(myEvent.getTime1());
        timeToggleButton.setTextOff(myEvent.getTime2());
        // Return the completed view to render on screen
        return convertView;
    }
}
