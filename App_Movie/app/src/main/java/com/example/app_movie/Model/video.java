package com.example.app_movie.Model;

public class video {
    int idVideo ;
    String phoneNumber, avatar , linkVideo, contentVideo ;

    public video() {
    }

    public video(int idVideo, String phoneNumber, String avatar, String linkVideo, String contentVideo) {
        this.idVideo = idVideo;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.linkVideo = linkVideo;
        this.contentVideo = contentVideo;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public String getContentVideo() {
        return contentVideo;
    }

    public void setContentVideo(String contentVideo) {
        this.contentVideo = contentVideo;
    }
}
