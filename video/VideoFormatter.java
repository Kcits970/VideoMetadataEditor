package video;

import static video.VideoTags.*;

public class VideoFormatter {

    public static String toFormattedTag(String tag) {
        return String.format("%%%s%%", tag);
    }

    public static String toFormattedDescription(Object description) {
        return description.toString().replaceAll("&", "&amp").replaceAll("%", "&per");
    }

    public static String toFormattedLine(String tag, Object description) {
        return toFormattedTag(tag) + toFormattedDescription(description);
    }

    public static String toFormattedStruct(Pseudovideo v) {
        StringBuilder builder = new StringBuilder();

        builder.append(toFormattedTag(tags.get(0)) + "\n");
        builder.append(toFormattedLine(tags.get(1), v.getTitle()) + "\n");
        builder.append(toFormattedLine(tags.get(2), v.getUploader()) + "\n");
        builder.append(toFormattedLine(tags.get(3), v.getViews()) + "\n");
        builder.append(toFormattedLine(tags.get(4), v.getLikes()) + "\n");
        builder.append(toFormattedLine(tags.get(5), v.getDislikes()));

        return builder.toString();
    }
}
