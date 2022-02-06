package frame.graphicsobj;

import java.awt.*;

public class MyRatioBar extends MyGraphicsObject {
    double ratio;

    public MyRatioBar(double ratio) {
        this.ratio = ratio;
        width = 100;
        height = 3;
    }

    @Override
    public void drawItself(Graphics g, int x, int y) {
        g.drawRect(x, y, width, height);
        g.fillRect(x, y, (int) (width * ratio), height);
    }
}
