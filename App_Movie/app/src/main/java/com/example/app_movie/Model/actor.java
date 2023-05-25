package com.example.app_movie.Model;

public class actor {
    int idActor , idMovie ;
    String nameActor , avatarActor ;

    public actor(int idActor, int idMovie, String nameActor, String avatarActor) {
        this.idActor = idActor;
        this.idMovie = idMovie;
        this.nameActor = nameActor;
        this.avatarActor = avatarActor;
    }

    public actor() {
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getNameActor() {
        return nameActor;
    }

    public void setNameActor(String nameActor) {
        this.nameActor = nameActor;
    }

    public String getAvatarActor() {
        return avatarActor;
    }

    public void setAvatarActor(String avatarActor) {
        this.avatarActor = avatarActor;
    }
}
