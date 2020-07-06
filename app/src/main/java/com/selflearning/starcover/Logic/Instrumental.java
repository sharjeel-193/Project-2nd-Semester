package com.selflearning.starcover.Logic;

public class Instrumental {

    private String songName;
    private String artistName;
    private int instrumentalImageId;
    private int duration;

    public Instrumental(String songName, String artistName, int instrumentalImageId) {
        this.songName = songName;
        this.artistName = artistName;
        this.instrumentalImageId = instrumentalImageId;
    }

    public Instrumental(String songName, String artistName, int instrumentalImageId, int duration) {
        this.songName = songName;
        this.artistName = artistName;
        this.instrumentalImageId = instrumentalImageId;
        this.duration = duration;
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
