package video;

import util.*;

import java.io.File;
import java.util.*;

public class VideoBase extends MyObservable {
    public static final String DEFAULT_FILENAME = "videos.xml";
    private Comparator<? super Video> comparator;
    private List<Video> videos;

    public VideoBase() {
        videos = new ArrayList<>();
        observers = new ArrayList<>();
        setComparator(VideoComparators.VIEW_COMPARATOR.reversed());
    }

    public void readFrom(String filename) {
        videos.clear();

        VideoReader reader = new VideoReader(new File(filename));
        for (Video video : reader)
            add(video, false);

        refreshSort();
    }

    public void add(Video video, boolean keepOrder) {
        if (video != null && !videos.contains(video)) {
            videos.add(video);

            if (keepOrder) refreshSort();
        }
    }

    public void edit(Video originalVideo, Video newVideo) {
        if (videos.contains(originalVideo)) {
            originalVideo.set(newVideo);
            refreshSort();
        }
    }

    public void delete(Video video) {
        if (videos.remove(video))
            notifyObservers();
    }

    public void setComparator(Comparator<? super Video> comparator) {
        this.comparator = comparator;
        refreshSort();
    }

    public void refreshSort() {
        videos.sort(comparator);
        notifyObservers();
    }

    public void writeTo(String filename) {
        try {
            new VideoWriter().writeAll(videos, new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notifyObservers() {
        for (MyObserver observer : observers) {
            observer.update(videos);
        }
    }
}
