package com.example.palendar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class User implements Parcelable {
    private String userID;
    private ArrayList<Time> preferredTimes;


    public User(String userID, ArrayList<Time> preferredTimes){
        this.userID = userID;
        this.preferredTimes = preferredTimes;
    }

    public User(){
        this.userID = null;
        this.preferredTimes = null;
    }


    protected User(Parcel in) {
        userID = in.readString();
        preferredTimes = in.createTypedArrayList(Time.CREATOR);
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


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<Time> getPreferredTimes() {
        return preferredTimes;
    }

    public void setPreferredTimes(ArrayList<Time> preferredTimes) {
        this.preferredTimes = preferredTimes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userID);
        dest.writeTypedList(preferredTimes);
    }

}
