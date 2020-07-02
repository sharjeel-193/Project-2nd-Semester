package com.selflearning.starcover.Logic;

public class Instrumental {

    private String songName;
    private String artistName;
    private int instrumentalImageId;

    public Instrumental(String songName, String artistName, int instrumentalImageId) {
        this.songName = songName;
        this.artistName = artistName;
        this.instrumentalImageId = instrumentalImageId;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getInstrumentalImageId() {
        return instrumentalImageId;
    }
}
