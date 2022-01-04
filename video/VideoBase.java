package video;

import io.*;
import util.*;

import java.util.*;

public class VideoBase extends MyObservable implements MyObserver {
    public static final String DEFAULT_FILE_LOCATION = "pseudovideos.txt";
    private Comparator<? super Pseudovideo> comparator;
    private List<Pseudovideo> videos;

    public VideoBase() {
        videos = new ArrayList<>();
        observers = new ArrayList<>();
        setComparator(VideoComparators.VIEW_COMPARATOR.reversed());
    }

    public void loadFrom(String filename) {
        videos.clear();

        try (UTF8Reader reader = UTF8Reader.getInstance(filename)) {
            VideoParser parser = new VideoParser(reader);

            while (true) {
                add(parser.nextVideo(), false);
                if (parser.fullyParsed()) break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        refreshSort();
    }

    public void add(Pseudovideo video, boolean keepOrder) {
        if (video != null && !videos.contains(video)) {
            video.addObserver(this);
            videos.add(video);

            if (keepOrder) refreshSort();
        }
    }

    public void delete(Pseudovideo video) {
        if (videos.remove(video))
            notifyObservers();
    }

    public void setComparator(Comparator<? super Pseudovideo> comparator) {
        this.comparator = comparator;
        refreshSort();
    }

    public void refreshSort() {
        videos.sort(comparator);
        notifyObservers();
    }

    public void saveTo(String filename) {
        try (UTF8Writer writer = UTF8Writer.getInstance(filename)) {
            StringBuilder builder = new StringBuilder();

            for (Pseudovideo v : videos) {
                builder.append(VideoFormatter.toFormattedStruct(v));
                builder.append('\n');
            }

            writer.write(builder.toString());
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notifyObservers() {
        for (MyObserver observer : observers) {
            observer.update(videos);
        }
    }

    @Override
    public void update(Object o) {
        refreshSort();
    }
}
