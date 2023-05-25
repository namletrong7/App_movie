package com.example.app_movie.Model;

import java.io.Serializable;

public class device implements Serializable {
    String idDevice ;
    String phoneNumber, nameDevice , timeDateSignIn  ;

    public device() {
    }

    public device(String idDevice, String phoneNumber, String nameDevice, String timeDateSignIn) {
        this.idDevice = idDevice;
        this.phoneNumber = phoneNumber;
        this.nameDevice = nameDevice;
        this.timeDateSignIn = timeDateSignIn;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
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
