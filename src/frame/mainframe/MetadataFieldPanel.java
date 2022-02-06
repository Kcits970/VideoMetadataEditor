package frame.mainframe;

import video.Video;
import frame.MyFonts;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.Stream;

public class MetadataFieldPanel extends JPanel implements Iterable<String> {
    JTextField titleField;
    JTextField uploaderField;
    JTextField viewsField;
    JTextField likesField;
    JTextField dislikesField;

    public MetadataFieldPanel() {
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

    public void clearFields() {
        Stream.of(titleField, uploaderField, viewsField, likesField, dislikesField).forEach(textfield -> textfield.setText(""));
    }

    public Iterator<String> iterator() {
        return Stream.of(titleField, uploaderField, viewsField, likesField, dislikesField).map(textfield -> textfield.getText()).iterator();
    }
}