import javax.swing.SwingUtilities;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StatsHandler statsHandler = new StatsHandler();
                new MyFrame(statsHandler);
            }
        });
    }
}
