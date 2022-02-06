package frame.graphicsobj;

import java.awt.Graphics;

public abstract class MyGraphicsObject {
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_RIGHT = 1;
    public int height;
    public int width;

    public abstract void drawItself(Graphics g, int x, int y);

    public static void vDrawGraphics(Graphics g, int x, int y, int align, MyGraphicsObject... objs) {
        //draws the given 'MyGraphicsObject's vertically on the specified (x, y) position.

        for (MyGraphicsObject obj : objs) {
            obj.drawItself(g, x - obj.width * align, y);
            y += obj.height;
        }
    }
}
