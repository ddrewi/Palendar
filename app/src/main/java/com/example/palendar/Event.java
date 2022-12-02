package com.example.palendar;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Event implements Parcelable {
    private String name;
    private String time1;
    private String time2;
    private String date;
    private ArrayList<User> users;
    private ArrayList<Time> times;
    private String docID;

    public Event(String name, String time1, String time2, String date, ArrayList<User> users, ArrayList<Time> times, String docID){
        this.name = name;
        this.time1 = time1;
        this.time2 = time2;
        this.date = date;
        this.users = users;
        this.times = times;
        this.docID = docID;
    }

    public Event(String name, String time1, String time2, String date, ArrayList<User> users, ArrayList<Time> times){
        this.name = name;
        this.time1 = time1;
        this.time2 = time2;
        this.date = date;
        this.users = users;
        this.times = times;
        this.docID = "No docID yet";
    }

    public Event(String name, String time1, String time2, String date, ArrayList<Time> times){
        this.name = name;
        this.time1 = time1;
        this.time2 = time2;
        this.date = date;
        this.users = new ArrayList<User>();
        this.times = times;
        this.docID = "No docID yet";
    }

    protected Event(Parcel in) {
        name = in.readString();
        time1 = in.readString();
        time2 = in.readString();
        date = in.readString();
        times = in.createTypedArrayList(Time.CREATOR);
        docID = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
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

    public ArrayList<Time> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<Time> times) {
        this.times = times;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(time1);
        dest.writeString(time2);
        dest.writeString(date);
        dest.writeTypedList(times);
    }
}
