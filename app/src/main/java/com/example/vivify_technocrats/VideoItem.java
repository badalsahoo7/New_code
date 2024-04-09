package com.example.vivify_technocrats;

public class VideoItem {
   // private int videoThumbnail;
    private String videoTitle;
    private String channelName;
    private int channelImage;

//    public int getVideoThumbnail() {
//        return videoThumbnail;
//    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getChannelName() {
        return channelName;
    }

    public int getChannelImage() {
        return channelImage;
    }

    public VideoItem(String videoTitle, String channelName, int channelImage) {
        //this.videoThumbnail = videoThumbnail;
        this.videoTitle = videoTitle;
        this.channelName = channelName;
        this.channelImage = channelImage;
    }
}
