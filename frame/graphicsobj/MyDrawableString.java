package frame.graphicsobj;

import frame.MyFrameTools;
import frame.MyFonts;

import java.awt.*;

public class MyDrawableString extends MyGraphicsObject {
    static final int STRING_MAX_LENGTH = 400; //length here is in terms of pixels, not characters!
    String string;
    Font font;

    public MyDrawableString(String s, Font f, Graphics g) {
        string = MyFrameTools.truncate(s, f, g, STRING_MAX_LENGTH);
        font = f;
        width = MyFonts.getStringWidth(s, f, g);
        height = MyFonts.getFontHeight(f, g);
    }

    @Override
    public void drawItself(Graphics g, int x, int y) {
        g.setFont(font);
        g.drawString(string, x, y + g.getFontMetrics().getMaxAscent());
    }
}
