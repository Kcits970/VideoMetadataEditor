package video;

import java.util.HashMap;

public class VideoTags {
    public static final HashMap<Integer, String> tags;

    static {
        tags = new HashMap<>();
        tags.put(0, "pseudovideo");
        tags.put(1, "title");
        tags.put(2, "uploader");
        tags.put(3, "views");
        tags.put(4, "likes");
        tags.put(5, "dislikes");
    }
}
