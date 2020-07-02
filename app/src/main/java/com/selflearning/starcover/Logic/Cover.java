package com.selflearning.starcover.Logic;

public class Cover {
    //Original code
//    private UserProfile user;
//    private Instrumental instrumental;
//    private String duration;
//    private int coverLikes;
//    public Cover(Instrumental instrumental, int coverLikes, UserProfile user, String duration) {
//        this.instrumental = instrumental;
//        this.coverLikes = coverLikes;
//        this.user = user;
//        this.duration = duration;
//    }
//    public Instrumental getInstrumental() {
//        return instrumental;
//    }
//    public int getCoverLikes() {
//        return coverLikes;
//    }
//    public UserProfile getUser() {
//        return user;
//    }
//    public String getDuration() {
//        return duration;
//    }

    //Code for testing UI
    private String userId;
    private int userDp;
    private String coverName;
    private String coverArtist;
    private String coverDuration;
    private String coverLikes;
    private int coverThumbnail;

    public Cover(String userId, int userDp, String coverName, String coverArtist, String coverDuration, String coverLikes, int coverThumbnail) {
        this.userId = userId;
        this.userDp = userDp;
        this.coverName = coverName;
        this.coverArtist = coverArtist;
        this.coverDuration = coverDuration;
        this.coverLikes = coverLikes;
        this.coverThumbnail = coverThumbnail;
    }

    public String getUserId() {
        return userId;
    }

    public int getUserDp() {
        return userDp;
    }

    public String getCoverName() {
        return coverName;
    }

    public String getCoverArtist() {
        return coverArtist;
    }

    public String getCoverDuration() {
        return coverDuration;
    }

    public String getCoverLikes() {
        return coverLikes;
    }

    public int getCoverThumbnail() {
        return coverThumbnail;
    }
}
