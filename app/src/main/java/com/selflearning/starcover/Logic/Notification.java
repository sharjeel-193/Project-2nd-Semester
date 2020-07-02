package com.selflearning.starcover.Logic;

public class Notification {

    private String heading;
    private String text;
    private int thumbnailId;

    public Notification(String heading, String text, int thumbnailId) {
        this.heading = heading;
        this.text = text;
        this.thumbnailId = thumbnailId;
    }

    public String getHeading() {
        return heading;
    }

    public String getText() {
        return text;
    }

    public int getThumbnailId() {
        return thumbnailId;
    }
}
