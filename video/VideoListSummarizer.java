package video;

import java.util.List;

public class VideoListSummarizer {
    public static Pseudovideo findMostViewedVideo(List<Pseudovideo> videos) {
        return videos.stream().max(VideoComparators.VIEW_COMPARATOR).orElse(Pseudovideo.NULL_VIDEO);
    }

    public static Pseudovideo findLeastViewedVideo(List<Pseudovideo> videos) {
        return videos.stream().min(VideoComparators.VIEW_COMPARATOR).orElse(Pseudovideo.NULL_VIDEO);
    }

    public static double getAverageViews(List<Pseudovideo> videos) {
        return videos.stream().mapToInt(video->video.getViews()).average().orElse(0);
    }
}
