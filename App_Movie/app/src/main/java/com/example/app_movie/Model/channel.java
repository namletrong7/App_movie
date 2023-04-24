package com.example.app_movie.Model;

public class channel {
    int idChannel ;
    String nameChannel,thumbnailChannel,linkChannel,typeChannel,contentChannel ;

    public channel(int idChannel, String nameChannel, String thumbnailChannel, String linkChannel, String typeChannel, String contentChannel) {
        this.idChannel = idChannel;
        this.nameChannel = nameChannel;
        this.thumbnailChannel = thumbnailChannel;
        this.linkChannel = linkChannel;
        this.typeChannel = typeChannel;
        this.contentChannel = contentChannel;
    }

    public int getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(int idChannel) {
        this.idChannel = idChannel;
    }

    public String getNameChannel() {
        return nameChannel;
    }

    public void setNameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
    }

    public String getThumbnailChannel() {
        return thumbnailChannel;
    }

    public void setThumbnailChannel(String thumbnailChannel) {
        this.thumbnailChannel = thumbnailChannel;
    }

    public String getLinkChannel() {
        return linkChannel;
    }

    public void setLinkChannel(String linkChannel) {
        this.linkChannel = linkChannel;
    }

    public String getTypeChannel() {
        return typeChannel;
    }

    public void setTypeChannel(String typeChannel) {
        this.typeChannel = typeChannel;
    }

    public String getContentChannel() {
        return contentChannel;
    }

    public void setContentChannel(String contentChannel) {
        this.contentChannel = contentChannel;
    }

    public channel() {
    }
}
