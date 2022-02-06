package frame.prompt;

import video.VideoBase;
import frame.MyFrameTools;

import javax.swing.*;
import java.awt.*;

public class FilenamePrompt extends JDialog {
    private JTextField filenameField;
    private JButton okButton;
    private JButton cancelButton;
    private JButton defaultButton;

    private String filename;

    public FilenamePrompt(Frame owner) {
        super(owner, "Filename Prompt", true);

        filenameField = new JTextField(20);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        defaultButton = new JButton("Default");

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
        c.add(new JLabel("Filename:"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        c.add(filenameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        c.add(MyFrameTools.getHorizontalContainer(true, okButton, cancelButton, defaultButton), constraints);

        setContentPane(MyFrameTools.getEdgeSpacedContainer(c));
    }

    private void bindActions() {
        defaultButton.addActionListener(e -> {
            filenameField.setText(VideoBase.DEFAULT_FILENAME);
        });

        cancelButton.addActionListener(e -> {
            setVisible(false);
        });

        okButton.addActionListener(e -> {
            filenameField.setText(filenameField.getText().replaceAll(" ", ""));
            filename = filenameField.getText();
            setVisible(false);
        });
    }

    public String getFilenameInput() {
        filename = null;
        setVisible(true);
        return filename;
    }
}
