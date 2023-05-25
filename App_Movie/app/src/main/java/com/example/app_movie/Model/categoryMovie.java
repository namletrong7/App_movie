package com.example.app_movie.Model;

import java.util.ArrayList;
import java.util.List;

public class categoryMovie {
    private String category ;
    private ArrayList<movie> listMovie ;

    public categoryMovie(String category, ArrayList<movie> listMovie) {
        this.category = category;
        this.listMovie = listMovie;
    }

    public categoryMovie() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<movie> listMovie) {
        this.listMovie = listMovie;
    }
}
