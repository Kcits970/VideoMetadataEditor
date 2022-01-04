package video;

import java.util.Comparator;

public class VideoComparators {
    public static final Comparator<Pseudovideo> VIEW_COMPARATOR;
    public static final Comparator<Pseudovideo> TITLE_COMPARATOR;
    public static final Comparator<Pseudovideo> RATIO_COMPARATOR;

    static {
        TITLE_COMPARATOR = (o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle());
        VIEW_COMPARATOR = (o1, o2) -> Integer.compare(o1.getViews(), o2.getViews());
        RATIO_COMPARATOR = (o1, o2) -> Double.compare(o1.getRatio(), o2.getRatio());
    }
}