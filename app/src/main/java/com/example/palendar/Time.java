package com.example.palendar;

import android.os.Parcel;
import android.os.Parcelable;

public class Time implements Parcelable {
    private String time;
    private int counter;


    public Time(String time) {
        this.time = time;
        this.counter = 0;
    }

    protected Time(Parcel in) {
        time = in.readString();
        counter = in.readInt()
    }

    public static final Creator<Time> CREATOR = new Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeInt(counter);
    }
}
