package com.example.app_movie.Model;

public class historySearch {
    String phoneNumber , contentSearch ;

    public historySearch(String phoneNumber, String contentSearch) {
        this.phoneNumber = phoneNumber;
        this.contentSearch = contentSearch;
    }

    public historySearch() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContentSearch() {
        return contentSearch;
    }

    public void setContentSearch(String contentSearch) {
        this.contentSearch = contentSearch;
    }
}
