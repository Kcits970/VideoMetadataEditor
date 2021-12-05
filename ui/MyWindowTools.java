package ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MyWindowTools {
    public static Border createMyBorder(String title) {
        Border baseBorder = BorderFactory.createLineBorder(new Color(184, 207, 229));
        return BorderFactory.createTitledBorder(baseBorder, title, TitledBorder.LEFT, TitledBorder.TOP, MyFonts.CONSOLAS_16);
    }

    public static JPanel getBorderSpacedPanel(JComponent component) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
        panel.add(Box.createHorizontalStrut(5), BorderLayout.WEST);
        panel.add(Box.createVerticalStrut(5), BorderLayout.NORTH);
        panel.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
        panel.add(component, BorderLayout.CENTER);

        return panel;
    }

    public static void truncateJLabel(JLabel label, int maxWidth) {
        if (label.getPreferredSize().width < maxWidth)
            return;

        String text = label.getText();

        while (label.getPreferredSize().width > maxWidth) {
            text = text.substring(0, text.length() - 1);
            label.setText(text + "...");
        }
    }
}

class MyFonts {
    public static final Font CONSOLAS_16;
    public static final Font ARIAL_UNICODE_18;
    public static final Font ARIAL_UNICODE_14;

    static {
        CONSOLAS_16 = new Font("Consolas", Font.PLAIN, 16);
        ARIAL_UNICODE_18 = new Font("Arial Unicode MS", Font.PLAIN, 18);
        ARIAL_UNICODE_14 = new Font("Arial Unicode MS", Font.PLAIN, 14);
    }
}