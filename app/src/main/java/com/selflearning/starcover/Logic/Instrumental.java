package com.selflearning.starcover.Logic;

public class Instrumental {

    private String songName;
    private String artistName;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private int instrumentalImageId;
    private int duration;

    public Instrumental(String songName, String artistName, int instrumentalImageId, String url) {
        this.songName = songName;
        this.artistName = artistName;
        this.instrumentalImageId = instrumentalImageId;
        this.url = url;
    }

    public Instrumental(String songName, String artistName, int instrumentalImageId, int duration, String url) {
        this.songName = songName;
        this.artistName = artistName;
        this.instrumentalImageId = instrumentalImageId;
        this.duration = duration;
        this.url = url;
    }

    public String getSongName() {
        return songName;
    }

    public int getDuration() {
        return duration;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getInstrumentalImageId() {
        return instrumentalImageId;
    }
}
