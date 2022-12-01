package com.example.palendar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {
    private ArrayList<Time> preferredTimes;
    private String userID;



    public User(ArrayList<Time> preferredTimes, String userID) {
        this.preferredTimes = preferredTimes;
        this.userID = userID;
    }


    protected User(Parcel in) {
        preferredTimes = in.createTypedArrayList(Time.CREATOR);
        userID = in.readString();
    }


    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public ArrayList<Time> getPreferredTimes() {
        return preferredTimes;
    }

    public void setPreferredTimes(ArrayList<Time> preferredTimes) {
        this.preferredTimes = preferredTimes;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(preferredTimes);
        dest.writeString(userID);
    }

}
