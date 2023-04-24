package com.example.app_movie.Model;

public class comment {
    int idComment,idMovie ;
    String phoneNumber ;
    String contentComment;
    String avatar ;

    public comment(Integer o, int id, String phone, String content, String avatar) {
    }

    public comment(int idComment, int idMovie, String phoneNumber, String contentComment,String avatar) {
        this.idComment = idComment;
        this.idMovie = idMovie;
        this.phoneNumber = phoneNumber;
        this.contentComment = contentComment;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
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

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }
}
