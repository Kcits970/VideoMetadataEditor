package video;

import java.util.Comparator;

public class VideoComparators {
    public static final Comparator<Video> VIEW_COMPARATOR;
    public static final Comparator<Video> TITLE_COMPARATOR;

    static {
        TITLE_COMPARATOR = new TitleComparator();
        VIEW_COMPARATOR = new ViewComparator();
    }
}

class TitleComparator implements Comparator<Video> {
    @Override
    public int compare(Video o1, Video o2) {
        return CharSequence.compare(o1.getTitle(), o2.getTitle());
    }
}

class ViewComparator implements Comparator<Video> {
    @Override
    public int compare(Video o1, Video o2) { //views are compared in descending order
        return Integer.compare(o2.getViews(), o1.getViews());
    }
}
