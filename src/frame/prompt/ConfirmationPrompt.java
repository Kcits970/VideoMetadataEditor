package frame.prompt;

import frame.MyFrameTools;

import javax.swing.*;
import java.awt.*;

public class ConfirmationPrompt extends JDialog {
    private String confirmationMessage;
    private JButton yesButton;
    private JButton noButton;

    private boolean confirmation;

    public ConfirmationPrompt(Frame owner, String message) {
        super(owner, "Confirmation Prompt", true);

        confirmationMessage = message;
        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        bindActions();
        addComponents();
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
    }

    private void addComponents() {
        Container c = getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2,2,2,2);
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        c.add(new JLabel(confirmationMessage), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        c.add(MyFrameTools.getHorizontalContainer(true, yesButton, noButton), constraints);

        setContentPane(MyFrameTools.getEdgeSpacedContainer(c));
    }

    private void bindActions() {
        yesButton.addActionListener(e -> {
            confirmation = true;
            setVisible(false);
        });

        noButton.addActionListener(e -> {
            setVisible(false);
        });
    }

    public boolean getConfirmation() {
        confirmation = false;
        setVisible(true);
        return confirmation;
    }
}
