import frame.mainframe.MyFrame;
import video.VideoBase;

public class Launcher {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new MyFrame(new VideoBase()));
    }
}
