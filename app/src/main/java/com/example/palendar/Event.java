package com.example.palendar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Event {
    private String name;
    private String time1;
    private String time2;
    private ArrayList<String> timesToDisplay;

    public Event(String name, String time1, String time2, ArrayList<String> timesToDisplay){
        this.name = name;
        this.time1 = time1;
        this.time2 = time2;
        this.timesToDisplay = timesToDisplay;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }
}
