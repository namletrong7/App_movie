package com.example.app_movie.Model;

public class user {

    String phoneNumber, password, nameUser, sex, birthday, avatar ;
    int numberDevice ;   // giới hạn chỉ có 3 thiết bị được đăng nhập

    public user() {
    }

    public user(String phoneNumber, String password, String nameUser, String sex, String birthday, String avatar, int numberDevice) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.nameUser = nameUser;
        this.sex = sex;
        this.birthday = birthday;
        this.avatar = avatar;
        this.numberDevice = numberDevice;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getNumberDevice() {
        return numberDevice;
    }

    public void setNumberDevice(int numberDevice) {
        this.numberDevice = numberDevice;
    }
}
