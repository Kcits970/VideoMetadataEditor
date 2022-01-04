package video;

import util.*;

import java.util.Iterator;

public class Pseudovideo extends MyObservable {
    public static final Pseudovideo NULL_VIDEO;
    static {
        NULL_VIDEO = new Pseudovideo("NULL", "NULL", 0, 0, 0);
    }

    private String title;
    private String uploader;
    private int views;
    private int likes;
    private int dislikes;

    public Pseudovideo(String name, String uploader, int views, int likes, int dislikes) {
        this.title = name;
        this.uploader = uploader;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Pseudovideo(Iterator<String> metadata) {
        setMetadata(metadata);
    }

    public void setMetadata(Iterator<String> metadata) {
        setTitle(metadata.next());
        setUploader(metadata.next());
        setViews(MyNumberParser.parseOptionalInt(metadata.next()).orElse(views));
        setLikes(MyNumberParser.parseOptionalInt(metadata.next()).orElse(likes));
        setDislikes(MyNumberParser.parseOptionalInt(metadata.next()).orElse(dislikes));

        notifyObservers();
    }

    public void setTitle(String s) {
        if (s.isBlank())
            s = "NULL";
        title = s;
    }
    public void setUploader(String s) {
        if (s.isBlank())
            s = "NULL";
        uploader = s;
    }
    public void setViews(int n) {views = n;}
    public void setLikes(int n) {likes = n;}
    public void setDislikes(int n) {dislikes = n;}

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

        Pseudovideo video = (Pseudovideo) o;
        return this.title.equals(video.title) && this.uploader.equals(video.uploader);
    }

    @Override
    public void notifyObservers() {
        for (MyObserver observer : observers)
            observer.update(this);
    }
}