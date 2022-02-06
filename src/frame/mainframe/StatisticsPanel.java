package frame.mainframe;

import video.*;
import frame.*;
import util.MyObserver;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.List;

public class StatisticsPanel extends JPanel implements MyObserver {
    JTextArea textArea;

    public StatisticsPanel() {
        textArea = new JTextArea(9, 30);
        textArea.setFont(MyFonts.ARIAL_UNICODE_14);
        textArea.setEditable(false);

        addComponents();
        setBorder(MyFrameTools.createMyTitledBorder("Statistics"));
    }

    @Override
    public void update(Object o) {
        writeStatistics((List<Video>) o);
    }

    public void addComponents() {
        JScrollPane scrollPane = new JScrollPane(textArea
                , ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
                , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout());
        add(scrollPane);
    }

    public void writeStatistics(List<Video> videos) {
        Video mostViewed = VideoListSummarizer.findMostViewedVideo(videos);
        Video leastViewed = VideoListSummarizer.findLeastViewedVideo(videos);

        StringBuilder builder = new StringBuilder();

        builder.append(String.format("\tStatistics of %d video(s)\n", videos.size()));
        builder.append('\n');

        builder.append(String.format("Most viewed video: '%s'\n", mostViewed.getTitle()));
        builder.append(String.format("%d view(s)\n", mostViewed.getViews()));
        builder.append('\n');

        builder.append(String.format("Least viewed video: '%s'\n", leastViewed.getTitle()));
        builder.append(String.format("%d view(s)\n", leastViewed.getViews()));
        builder.append('\n');

        builder.append(String.format("Average views: %.2f", VideoListSummarizer.getAverageViews(videos)));

        textArea.setText(builder.toString());
    }
}
