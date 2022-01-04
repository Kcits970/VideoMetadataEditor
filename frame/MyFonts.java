package frame;

import java.awt.*;

public class MyFonts {
    public static final Font CONSOLAS_16;
    public static final Font ARIAL_UNICODE_20;
    public static final Font ARIAL_UNICODE_18;
    public static final Font ARIAL_UNICODE_14;

    static {
        CONSOLAS_16 = new Font("Consolas", Font.PLAIN, 16);
        ARIAL_UNICODE_20 = new Font("Arial Unicode MS", Font.PLAIN, 20);
        ARIAL_UNICODE_18 = new Font("Arial Unicode MS", Font.PLAIN, 18);
        ARIAL_UNICODE_14 = new Font("Arial Unicode MS", Font.PLAIN, 14);
    }

    public static int getFontHeight(Font f, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(f);
        return metrics.getMaxAscent() + metrics.getMaxDescent();
    }

    public static int getStringWidth(String s, Font f, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(f);
        return metrics.stringWidth(s);
    }
}
