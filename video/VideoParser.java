package video;

import static video.VideoTags.*;
import io.UTF8Reader;

public class VideoParser {
    UTF8Reader reader;

    public VideoParser(UTF8Reader reader) {
        this.reader = reader;
    }

    public Pseudovideo nextVideo() {
        try {
            findTag(tags.get(0));

            return new Pseudovideo(
                    extractTagInformation(tags.get(1)),
                    extractTagInformation(tags.get(2)),
                    Integer.parseInt(extractTagInformation(tags.get(3))),
                    Integer.parseInt(extractTagInformation(tags.get(4))),
                    Integer.parseInt(extractTagInformation(tags.get(5)))
            );
        } catch (Exception e) {
            return null;
        }
    }

    public void findTag(String tag) throws Exception {
        if (!reader.readUntil('%') || !reader.eat(tag) || !reader.readUntil('%'))
            throw new Exception(String.format("Failed to find occurrence of '%%%s%%'", tag));
    }

    public String extractTagInformation(String tag) throws Exception {
        findTag(tag);
        return reader.readLine().replaceAll("&per", "%").replaceAll("&amp", "&");
    }

    public boolean fullyParsed() throws Exception {
        return reader.isAtEOF();
    }
}
