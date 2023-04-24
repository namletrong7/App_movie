package com.example.app_movie.Model;

public class episode {
    int idEpisode , idMovie ;
    String linkEpisode , nameEpisode , thumbnailEpisode ;
    int numberEpisode;

    public episode(int idEpisode, int idMovie, String linkEpisode, String nameEpisode, String thumbnailEpisode, int numberEpisode) {
        this.idEpisode = idEpisode;
        this.idMovie = idMovie;
        this.linkEpisode = linkEpisode;
        this.nameEpisode = nameEpisode;
        this.thumbnailEpisode = thumbnailEpisode;
        this.numberEpisode = numberEpisode;
    }

    public episode() {
    }

    public int getIdEpisode() {
        return idEpisode;
    }

    public void setIdEpisode(int idEpisode) {
        this.idEpisode = idEpisode;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getLinkEpisode() {
        return linkEpisode;
    }

    public void setLinkEpisode(String linkEpisode) {
        this.linkEpisode = linkEpisode;
    }

    public String getNameEpisode() {
        return nameEpisode;
    }

    public void setNameEpisode(String nameEpisode) {
        this.nameEpisode = nameEpisode;
    }

    public String getThumbnailEpisode() {
        return thumbnailEpisode;
    }

    public void setThumbnailEpisode(String thumbnailEpisode) {
        this.thumbnailEpisode = thumbnailEpisode;
    }

    public int getNumberEpisode() {
        return numberEpisode;
    }

    public void setNumberEpisode(int numberEpisode) {
        this.numberEpisode = numberEpisode;
    }
}
