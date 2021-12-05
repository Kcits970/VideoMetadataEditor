package video;

import io.UTF8Reader;
import io.UTF8Writer;

import java.util.*;

public class VideoStatsHandler {
    private static final String LOAD_LOCATION = "videolist.txt";
    private List<Video> videos;

    public VideoStatsHandler() {
        videos = new ArrayList<>();
        loadVideos();
        sortBy(VideoComparators.VIEW_COMPARATOR);
    }

    private void loadVideos() {
        try (UTF8Reader reader = UTF8Reader.getInstance(LOAD_LOCATION)) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Video currentVideo = Video.getInstanceFromString(line);
                if (currentVideo != null) videos.add(currentVideo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Video> getVideos() {return videos;}

    public void sortBy(Comparator<? super Video> comparator) {
        Collections.sort(videos, comparator);
    }

    public int getNumberOfVideos() {return videos.size();}

    public Video findMostViewedVideo() {
        if (videos.size() == 0) return null;

        Video max = videos.get(0);
        for (Video v : videos) {
            if (v.getViews() > max.getViews())
                max = v;
        }

        return max;
    }

    public Video findLeastViewedVideo() {
        if (videos.size() == 0) return null;

        Video min = videos.get(0);
        for (Video v : videos) {
            if (v.getViews() < min.getViews())
                min = v;
        }

        return min;
    }

    public double getAverageViews() {
        int sum = 0;

        for (Video v : videos)
            sum += v.getViews();

        return (double) sum / videos.size();
    }


    public void saveToFile() {
        try (UTF8Writer writer = UTF8Writer.getInstance(LOAD_LOCATION)) {
            StringBuilder builder = new StringBuilder();

            for (Video v : videos) {
                builder.append(v.toFormattedString());
                builder.append('\n');
            }

            writer.write(builder.toString());
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
