package ui;

import video.Video;

import javax.swing.*;
import java.awt.*;

public class TextFieldPanel extends JPanel {
    JTextField titleField;
    JTextField uploaderField;
    JTextField viewsField;
    JTextField likesField;
    JTextField dislikesField;

    public TextFieldPanel() {
        titleField = new JTextField(30);
        uploaderField = new JTextField(30);
        viewsField = new JTextField(30);
        likesField = new JTextField(30);
        dislikesField = new JTextField(30);

        addComponents();
    }

    private void addComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        String[] labelTexts = {"Title:", "Uploader:", "Views:", "Likes:", "Dislikes:"};
        for (int i = 0; i < labelTexts.length; i++) {
            JLabel label = new JLabel(labelTexts[i]);
            label.setAlignmentX(LEFT_ALIGNMENT);
            label.setFont(MyFonts.ARIAL_UNICODE_14);

            constraints.gridx = 0;
            constraints.gridy = i;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.ipadx = 5;

            add(label, constraints);
        }

        Component[] components = {titleField, uploaderField, viewsField, likesField, dislikesField};
        for (int i = 0; i < components.length; i++) {
            components[i].setFont(MyFonts.ARIAL_UNICODE_14);
            constraints.gridx = 1;
            constraints.gridy = i;

            add(components[i], constraints);
        }
    }

    public void setTextFieldValues(Video v) {
        if (v == null) return;

        titleField.setText(v.getTitle());
        uploaderField.setText(v.getUploader());
        viewsField.setText(String.valueOf(v.getViews()));
        likesField.setText(String.valueOf(v.getLikes()));
        dislikesField.setText(String.valueOf(v.getDislikes()));

        titleField.setCaretPosition(0);
        uploaderField.setCaretPosition(0);
    }

    public void changeVideoAttributes(Video v) {
        try {
            int views = Integer.parseInt(viewsField.getText());
            int likes = Integer.parseInt(likesField.getText());
            int dislikes = Integer.parseInt(dislikesField.getText());

            v.setTitle(titleField.getText());
            v.setUploader(uploaderField.getText());
            v.setViews(views);
            v.setLikes(likes);
            v.setDislikes(dislikes);
        } catch (Exception e) {
            System.err.println("Failed to apply changes.");
        }
    }
}