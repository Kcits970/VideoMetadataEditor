package video;

import org.w3c.dom.*;

import java.io.File;
import java.util.*;

public class VideoReader implements Iterable<Video> {
    List<Video> videos;

    public VideoReader(File source) {
        videos = new ArrayList<>();
        readAll(source);
    }

    void readAll(File source) {
        try {
            Document document = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(source);
            NodeList nodeList = document.getElementsByTagName("video");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element currentElem = (Element) nodeList.item(i);
                videos.add(new Video(Arrays.asList(
                        currentElem.getElementsByTagName("title").item(0).getTextContent(),
                        currentElem.getElementsByTagName("uploader").item(0).getTextContent(),
                        currentElem.getElementsByTagName("views").item(0).getTextContent(),
                        currentElem.getElementsByTagName("likes").item(0).getTextContent(),
                        currentElem.getElementsByTagName("dislikes").item(0).getTextContent()
                ).iterator()));
            }
        } catch (Exception e) {}
    }

    @Override
    public Iterator<Video> iterator() {
        return videos.iterator();
    }
}
