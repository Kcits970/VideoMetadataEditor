import ui.MyFrame;
import video.VideoStatsHandler;

public class Launcher {
    public static void main(String[] args) {

        //To ensure thread safety, execute the tasks in the EDT(Event Dispatching Thread)
        javax.swing.SwingUtilities.invokeLater(() -> {
            VideoStatsHandler statsHandler = new VideoStatsHandler();
            new MyFrame(statsHandler);
        });
    }
}
