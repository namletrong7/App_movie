package com.example.app_movie.Model;

public class favoriteMovie {  // phim được đưa vào danh sách của người xem để sau có thể xem lại
    int idFavorite , idMovie ;
    String phoneNumber, thumbnailMovie ;

    public favoriteMovie(int idFavorite, int idMovie, String phoneNumber, String thumbnailMovie) {
        this.idFavorite = idFavorite;
        this.idMovie = idMovie;
        this.phoneNumber = phoneNumber;
        this.thumbnailMovie = thumbnailMovie;
    }
    public favoriteMovie() {
    }

    public int getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(int idFavorite) {
        this.idFavorite = idFavorite;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getThumbnailMovie() {
        return thumbnailMovie;
    }

    public void setThumbnailMovie(String thumbnailMovie) {
        this.thumbnailMovie = thumbnailMovie;
    }
}
