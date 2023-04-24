package com.example.app_movie.Model;

import java.io.Serializable;

public class movie implements Serializable {
    int idMovie ;
    String nameMovie,thumbnailMovie, coverMovie, contentMoive, categoryMovie,yearMovie, director;
    int view ;

    public movie() {
    }

    public movie(int idMovie, String nameMovie, String thumbnailMovie, String coverMovie, String contentMoive, String categoryMovie, String yearMovie, String director, int view) {
        this.idMovie = idMovie;
        this.nameMovie = nameMovie;
        this.thumbnailMovie = thumbnailMovie;
        this.coverMovie = coverMovie;
        this.contentMoive = contentMoive;
        this.categoryMovie = categoryMovie;
        this.yearMovie = yearMovie;
        this.director = director;
        this.view = view;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getThumbnailMovie() {
        return thumbnailMovie;
    }

    public void setThumbnailMovie(String thumbnailMovie) {
        this.thumbnailMovie = thumbnailMovie;
    }

    public String getCoverMovie() {
        return coverMovie;
    }

    public void setCoverMovie(String coverMovie) {
        this.coverMovie = coverMovie;
    }

    public String getContentMoive() {
        return contentMoive;
    }

    public void setContentMoive(String contentMoive) {
        this.contentMoive = contentMoive;
    }

    public String getCategoryMovie() {
        return categoryMovie;
    }

    public void setCategoryMovie(String categoryMovie) {
        this.categoryMovie = categoryMovie;
    }

    public String getYearMovie() {
        return yearMovie;
    }

    public void setYearMovie(String yearMovie) {
        this.yearMovie = yearMovie;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}
