package video;

import java.util.List;

public class VideoListSummarizer {
    public static Video findMostViewedVideo(List<Video> videos) {
        return videos.stream().max(VideoComparators.VIEW_COMPARATOR).orElse(Video.NULL_VIDEO);
    }

    public static Video findLeastViewedVideo(List<Video> videos) {
        return videos.stream().min(VideoComparators.VIEW_COMPARATOR).orElse(Video.NULL_VIDEO);
    }

    public static double getAverageViews(List<Video> videos) {
        return videos.stream().mapToInt(video->video.getViews()).average().orElse(0);
    }
}
