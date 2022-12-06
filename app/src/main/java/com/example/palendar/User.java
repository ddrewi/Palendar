package com.example.palendar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class User implements Parcelable {
    private String userID;
    private boolean[] preferredTimes;


    public User(String userID, boolean[] preferredTimes){
        this.userID = userID;
        this.preferredTimes = preferredTimes;
    }


    protected User(Parcel in) {
        userID = in.readString();
        preferredTimes = in.createBooleanArray();
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

    public boolean[] getPreferredTimes() {
        return preferredTimes;
    }

    public void setPreferredTimes(boolean[] preferredTimes) {
        this.preferredTimes = preferredTimes;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userID);
        dest.writeBooleanArray(preferredTimes);
    }

}
