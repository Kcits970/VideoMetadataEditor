package ui;

import video.Video;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class DetailedVideoListPanel extends JPanel {
    List<VideoDetailsPanel> videoPanelList;

    public DetailedVideoListPanel() {
        videoPanelList = new ArrayList<>();
    }

    public void refreshPanels(List<Video> videos) {
        removeAll();
        videoPanelList.clear();

        for (int i = 0; i < videos.size(); i++)
            videoPanelList.add(new VideoDetailsPanel(videos.get(i), i+1));

        setupVideoPanels();
        validate();
    }

    private void setupVideoPanels() {
        JPanel nestedPanels = new JPanel();
        BoxLayout verticalLayout = new BoxLayout(nestedPanels, BoxLayout.Y_AXIS);
        nestedPanels.setLayout(verticalLayout);
        for (VideoDetailsPanel panel : videoPanelList) {
            nestedPanels.add(panel);
        }

        JScrollPane scrollPane = new JScrollPane(MyWindowTools.getBorderSpacedPanel(nestedPanels)
                , ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
                , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(0, 300));

        setLayout(new BorderLayout());
        add(scrollPane);

        setBorder(MyWindowTools.createMyBorder("Detailed Video List"));
    }
}

class VideoDetailsPanel extends JPanel {
    private static final int LABEL_MAX_WIDTH = 500;
    private Video myVideo;
    private int nthOrder;

    public VideoDetailsPanel(Video v, int nth) {
        myVideo = v;
        nthOrder = nth;
        addLabels();
    }

    public void addLabels() {
        JLabel titleLabel = new JLabel(myVideo.getTitle());
        titleLabel.setFont(MyFonts.ARIAL_UNICODE_18);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        MyWindowTools.truncateJLabel(titleLabel, LABEL_MAX_WIDTH);

        JLabel uploaderLabel = new JLabel(String.format("by '%s'", myVideo.getUploader()));
        uploaderLabel.setFont(MyFonts.ARIAL_UNICODE_14);
        uploaderLabel.setHorizontalAlignment(SwingConstants.LEFT);
        MyWindowTools.truncateJLabel(uploaderLabel, LABEL_MAX_WIDTH);

        JLabel numericalViewsLabel = new JLabel(String.format("%,3d", myVideo.getViews()));
        numericalViewsLabel.setFont(MyFonts.ARIAL_UNICODE_18);
        numericalViewsLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel literalViewsLabel = new JLabel("view(s)");
        literalViewsLabel.setFont(MyFonts.ARIAL_UNICODE_14);
        literalViewsLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(2, 1));
        westPanel.add(titleLabel);
        westPanel.add(uploaderLabel);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(2, 1));
        eastPanel.add(numericalViewsLabel);
        eastPanel.add(literalViewsLabel);

        setLayout(new BorderLayout());
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);

        setBorder(MyWindowTools.createMyBorder(String.format("Video %d", nthOrder)));
    }
}
