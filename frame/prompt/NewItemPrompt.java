package frame.prompt;

import video.Pseudovideo;
import frame.mainframe.MetadataFieldPanel;
import frame.MyFrameTools;

import javax.swing.*;
import java.awt.*;

public class NewItemPrompt extends JDialog {
    MetadataFieldPanel textfields;
    JButton addButton;
    JButton cancelButton;
    JButton clearButton;

    Pseudovideo newItem;

    public NewItemPrompt(Frame owner) {
        super(owner, "New Item Prompt", true);

        textfields = new MetadataFieldPanel();
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        clearButton = new JButton("Clear");

        bindActions();
        addComponents();
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
    }

    private void addComponents() {
        Container c = getContentPane();
        BoxLayout verticalLayout = new BoxLayout(c, BoxLayout.Y_AXIS);
        c.setLayout(verticalLayout);

        c.add(textfields);
        c.add(Box.createVerticalStrut(5));
        c.add(MyFrameTools.getHorizontalContainer(true, addButton, cancelButton, clearButton));
        setContentPane(MyFrameTools.getEdgeSpacedContainer(c));
    }

    private void bindActions() {
        addButton.addActionListener(e -> {
            newItem = new Pseudovideo(textfields.iterator());
            setVisible(false);
        });

        cancelButton.addActionListener(e -> setVisible(false));

        clearButton.addActionListener(e -> textfields.clearFields());
    }

    public Pseudovideo getNewItem() {
        newItem = null;
        setVisible(true);
        return newItem;
    }
}
