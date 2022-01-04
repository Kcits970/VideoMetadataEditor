package frame;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Arrays;

public class MyFrameTools {
    public static Border createMyTitledBorder(String title) {
        Border baseBorder = BorderFactory.createLineBorder(new Color(184, 207, 229));
        return BorderFactory.createTitledBorder(baseBorder, title, TitledBorder.LEFT, TitledBorder.TOP, MyFonts.CONSOLAS_16);
    }

    public static Container getEdgeSpacedContainer(Container container) {
        Container c = new Container();
        c.setLayout(new BorderLayout());
        c.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
        c.add(Box.createHorizontalStrut(5), BorderLayout.WEST);
        c.add(Box.createVerticalStrut(5), BorderLayout.NORTH);
        c.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
        c.add(container, BorderLayout.CENTER);

        return c;
    }

    public static String truncate(String s, Font f, Graphics g, int maxWidth) {
        if (MyFonts.getStringWidth(s, f, g) <= maxWidth)
            return s;

        int truncateLength = 0;
        for (;;) {
            String truncated = s.substring(0, s.length() - ++truncateLength) + "...";
            if (MyFonts.getStringWidth(truncated, f, g) <= maxWidth)
                return truncated;
        }
    }


    public static Container getHorizontalContainer(boolean alignWidths, Component... components) {
        //Returns a container containing horizontally added components.
        //If alignWidths is true, then the components all share the same width.

        Container c = new Container();
        c.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        if (alignWidths) {
            int greatestWidth = Arrays.stream(components).mapToInt(component -> component.getPreferredSize().width).max().orElse(0);
            Arrays.stream(components).forEach(component -> component.setPreferredSize(new Dimension(greatestWidth, component.getPreferredSize().height)));
        }
        Arrays.stream(components).forEach(component->c.add(component));

        return c;
    }
}