package com.example.app_movie.Model;

import java.io.Serializable;

public class device implements Serializable {
    int idDevice ;
            String phoneNumber, nameDevice , timeDateSignIn  ;

    public device() {
    }

    public device(int idDevice, String phoneNumber, String nameDevice, String timeDateSignIn) {
        this.idDevice = idDevice;
        this.phoneNumber = phoneNumber;
        this.nameDevice = nameDevice;
        this.timeDateSignIn = timeDateSignIn;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }



    public String getTimeDateSignIn() {
        return timeDateSignIn;
    }

    public void setTimeDateSignIn(String timeDateSignIn) {
        this.timeDateSignIn = timeDateSignIn;
    }
}
