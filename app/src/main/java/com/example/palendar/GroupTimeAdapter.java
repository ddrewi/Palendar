package com.example.palendar;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupTimeAdapter extends ArrayAdapter<Time> {

    public GroupTimeAdapter(Context context, ArrayList<Time> timeList){
        super(context, 0, timeList);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Time myTime = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_gradient_textview, parent, false);
        }

        TextView timeTV = (TextView) convertView.findViewById(R.id.itemGradientTextView);
        //timeTV.setText(myTime.getTime());

        // ********** NEEDS SOME WAY TO FIND TOTAL COUNT OF PEOPLE, 5 is a placeholder **********
        int opacityValue = ((int) ((myTime.getCounter() + (int) (Math.random() * 5 + 1) /1.0/ 5) * 255));


        // Convert rgb ints to hex: https://stackoverflow.com/questions/3607858/convert-a-rgb-color-value-to-a-hexadecimal-string


        String hex = String.format("#%02x%02x%02x%02x", opacityValue, 0, 255, 0);

        Log.d("peanuts", "" + hex);
        timeTV.setBackgroundColor(Color.parseColor(hex));
        timeTV.setText("" + opacityValue);
        return timeTV;
    }
}
