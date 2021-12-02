import javax.swing.*;
import java.awt.BorderLayout;

public class StatisticsPanel extends JPanel {
    JTextArea textArea;

    public StatisticsPanel() {
        textArea = new JTextArea(9, 30);
        textArea.setFont(MyWindowTools.ARIAL_UNICODE_14);
        textArea.setEditable(false);

        addTextArea();
        setBorder(MyWindowTools.createMyBorder("Statistics"));
    }

    public void addTextArea() {
        JScrollPane scrollPane = new JScrollPane(textArea
                , ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
                , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout());
        add(scrollPane);
    }

    public void writeStatistics(StatsHandler handler) {
        Video mostViewed = handler.findMostViewedVideo();
        Video leastViewed = handler.findLeastViewedVideo();

        StringBuilder builder = new StringBuilder();

        builder.append(String.format("\tStatistics of %d video(s)\n", handler.getNumberOfVideos()));
        builder.append('\n');

        builder.append(String.format("Most viewed video: '%s'\n", mostViewed.getTitle()));
        builder.append(String.format("%d view(s)\n", mostViewed.getViews()));
        builder.append('\n');

        builder.append(String.format("Least viewed video: '%s'\n", leastViewed.getTitle()));
        builder.append(String.format("%d view(s)\n", leastViewed.getViews()));
        builder.append('\n');

        builder.append(String.format("Average views: %.2f", handler.getAverageViews()));

        textArea.setText(builder.toString());
    }
}
