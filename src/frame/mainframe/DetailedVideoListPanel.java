package frame.mainframe;

import video.Video;
import frame.graphicsobj.*;
import util.MyObserver;
import static frame.MyFrameTools.*;
import static frame.MyFonts.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class DetailedVideoListPanel extends JPanel implements MyObserver {
    List<VideoDetailPanel> videoPanelList;

    public DetailedVideoListPanel() {
        videoPanelList = new ArrayList<>();
    }

    @Override
    public void update(Object o) {
        refreshPanels((List<Video>) o);
    }

    public void refreshPanels(List<Video> videos) {
        removeAll();
        videoPanelList.clear();

        for (int i = 0; i < videos.size(); i++)
            videoPanelList.add(new VideoDetailPanel(videos.get(i), i+1));

        setupVideoPanels();
        revalidate();
    }

    private void setupVideoPanels() {
        JPanel nestedPanels = new JPanel();
        BoxLayout verticalLayout = new BoxLayout(nestedPanels, BoxLayout.Y_AXIS);
        nestedPanels.setLayout(verticalLayout);
        for (VideoDetailPanel panel : videoPanelList) {
            nestedPanels.add(panel);
        }

        JScrollPane scrollPane = new JScrollPane(getEdgeSpacedContainer(nestedPanels)
                , ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
                , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(0, 300));

        setLayout(new BorderLayout());
        add(scrollPane);

        setBorder(createMyTitledBorder("Detailed Video List"));
    }
}

class VideoDetailPanel extends JPanel {
    private static final int PREFERRED_PANEL_HEIGHT = 80;
    private static final int EDGE_OFFSET = 10;
    private Video video;
    private int nthOrder;

    public VideoDetailPanel(Video v, int nth) {
        video = v;
        nthOrder = nth;
        setPreferredSize(new Dimension(getPreferredSize().width, PREFERRED_PANEL_HEIGHT));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, PREFERRED_PANEL_HEIGHT));
        setBorder(createMyTitledBorder(String.format("Video %d", nthOrder)));
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintNames(g2);
        paintViews(g2);
        paintRatio(g2);
    }

    private int getStartingYPos(MyGraphicsObject... objs) {
        double yPos = (PREFERRED_PANEL_HEIGHT + EDGE_OFFSET) / 2.0;
        for (MyGraphicsObject obj : objs)
            yPos -= obj.height / 2.0;

        return (int) yPos;
    }

    private void paintNames(Graphics g) {
        MyDrawableString title = new MyDrawableString(video.getTitle(), ARIAL_UNICODE_18, g);
        MyDrawableString uploader = new MyDrawableString(video.getUploader(), ARIAL_UNICODE_14, g);

        int yPos = getStartingYPos(title, uploader);
        MyGraphicsObject.vDrawGraphics(g, EDGE_OFFSET, yPos, MyGraphicsObject.ALIGN_LEFT, title, uploader);
    }

    private void paintViews(Graphics g) {
        MyDrawableString formattedViews = new MyDrawableString(String.format("%,3d", video.getViews()), ARIAL_UNICODE_18, g);
        MyDrawableString literalViews = new MyDrawableString("view(s)", ARIAL_UNICODE_14, g);

        int yPos = getStartingYPos(formattedViews, literalViews);
        MyGraphicsObject.vDrawGraphics(g, getWidth() - 120, yPos, MyGraphicsObject.ALIGN_RIGHT, formattedViews, literalViews);
    }

    private void paintRatio(Graphics g) {
        MyDrawableString percentage = new MyDrawableString(String.format("%.2f%%", video.getRatio() * 100), ARIAL_UNICODE_20, g);
        MyRatioBar ratioBar = new MyRatioBar(video.getRatio());

        int yPos = getStartingYPos(percentage, ratioBar);
        MyGraphicsObject.vDrawGraphics(g, getWidth() - EDGE_OFFSET, yPos, MyGraphicsObject.ALIGN_RIGHT, percentage, ratioBar);
    }
}
