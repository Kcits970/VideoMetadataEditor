package video;

import util.*;

import java.util.Iterator;

public class Video {
    public static final Video NULL_VIDEO = new Video("NULL", "NULL", 0, 0, 0);;

    private String title;
    private String uploader;
    private int views;
    private int likes;
    private int dislikes;

    public Video(String name, String uploader, int views, int likes, int dislikes) {
        setTitle(name);
        setUploader(uploader);
        setViews(views);
        setLikes(likes);
        setDislikes(dislikes);
    }

    public Video(Iterator<String> metadata) {
        setTitle(metadata.next());
        setUploader(metadata.next());
        setViews(MyNumberParser.parseOptionalInt(metadata.next()).orElse(views));
        setLikes(MyNumberParser.parseOptionalInt(metadata.next()).orElse(likes));
        setDislikes(MyNumberParser.parseOptionalInt(metadata.next()).orElse(dislikes));
    }

    public void setTitle(String s) {
        if (s == null || s.isBlank())
            s = "NULL";
        title = s;
    }
    public void setUploader(String s) {
        if (s == null || s.isBlank())
            s = "NULL";
        uploader = s;
    }
    public void setViews(int n) {if (n >= 0) views = n;}
    public void setLikes(int n) {if (n >= 0) likes = n;}
    public void setDislikes(int n) {if (n >= 0) dislikes = n;}

    public String getTitle() {return title;}
    public String getUploader() {return uploader;}
    public int getViews() {return views;}
    public int getLikes() {return likes;}
    public int getDislikes() {return dislikes;}
    public double getRatio() {return (double) likes / (likes + dislikes);}

    @Override
    public String toString() {return title;}

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;

        Video video = (Video) o;
        return this.title.equals(video.title) && this.uploader.equals(video.uploader);
    }
}