package video;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class VideoWriter {
    public void writeAll(List<Video> videos, File destination) throws Exception {
        Document document = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = document.createElement("videos");

        for (Video v : videos) {
            Element videoElem = document.createElement("video");
            Element title = document.createElement("title");
            Element uploader = document.createElement("uploader");
            Element views = document.createElement("views");
            Element likes = document.createElement("likes");
            Element dislikes = document.createElement("dislikes");

            title.setTextContent(v.getTitle());
            uploader.setTextContent(v.getUploader());
            views.setTextContent(String.valueOf(v.getViews()));
            likes.setTextContent(String.valueOf(v.getLikes()));
            dislikes.setTextContent(String.valueOf(v.getDislikes()));

            appendChildren(videoElem, title, uploader, views, likes, dislikes);
            root.appendChild(videoElem);
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(destination));
    }

    void appendChildren(Node parent, Node ... children) {
        for (Node child : children)
            parent.appendChild(child);
    }
}
