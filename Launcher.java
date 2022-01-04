import frame.mainframe.MyFrame;
import video.VideoBase;

public class Launcher {
    public static void main(String[] args) {

        //To ensure thread safety, execute the tasks in the EDT(Event Dispatching Thread)
        javax.swing.SwingUtilities.invokeLater(() -> {
            VideoBase base = new VideoBase();
            new MyFrame(base);
        });
    }
}
