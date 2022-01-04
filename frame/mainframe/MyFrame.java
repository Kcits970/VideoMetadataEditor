package frame.mainframe;

import video.*;
import frame.*;
import frame.prompt.*;
import util.MyObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyFrame extends JFrame {
    VideoBase videoBase;

    VideoSelectionPanel videoSelectionPanel;
    EditPanel editPanel;
    StatisticsPanel statsPanel;
    DetailedVideoListPanel detailedVideoListPanel;

    FilenamePrompt filenamePrompt;
    NewItemPrompt newItemPrompt;
    ConfirmationPrompt deletionPrompt;

    public MyFrame(VideoBase b) {
        videoBase = b;

        videoSelectionPanel = new VideoSelectionPanel();
        editPanel = new EditPanel();
        statsPanel = new StatisticsPanel();
        detailedVideoListPanel = new DetailedVideoListPanel();

        b.addObserver(videoSelectionPanel);
        b.addObserver(statsPanel);
        b.addObserver(detailedVideoListPanel);
        b.notifyObservers();

        filenamePrompt = new FilenamePrompt(this);
        newItemPrompt = new NewItemPrompt(this);
        deletionPrompt = new ConfirmationPrompt(this, "Delete selected video?");

        addComponents();
        configureSettings();
    }

    private void addComponents() {
        Container c = getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5,5,5,5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        c.add(videoSelectionPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        c.add(editPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        c.add(statsPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        c.add(detailedVideoListPanel, constraints);

        setContentPane(MyFrameTools.getEdgeSpacedContainer(c));
    }

    private void configureSettings() {
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pseudovideo Metadata Editor");
        setVisible(true);
    }

    class VideoSelectionPanel extends JPanel implements MyObserver {
        DefaultListModel<Pseudovideo> items;
        JList<Pseudovideo> selectionList;

        public VideoSelectionPanel() {
            items = new DefaultListModel();
            selectionList = new JList(items);
            selectionList.setFont(MyFonts.ARIAL_UNICODE_18);

            addComponents();
            bindActions();
        }

        @Override
        public void update(Object o) {
            Pseudovideo selectedVideo = getSelectedVideo();
            setItems((List<Pseudovideo>) o);
            setSelectedVideo(selectedVideo);
        }

        private void setItems(List<Pseudovideo> videos) {
            items.clear();
            for (Pseudovideo v : videos)
                items.addElement(v);
        }

        private void addComponents() {
            JScrollPane scrollPane = new JScrollPane(selectionList
                    , ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
                    , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(300, 0));

            setLayout(new BorderLayout());
            add(scrollPane);
            setBorder(MyFrameTools.createMyTitledBorder("Video Selection Panel"));
        }

        private void bindActions() {
            selectionList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    editPanel.metadataFieldPanel.setTextFieldValues(selectionList.getSelectedValue());
                }
            });
        }

        public void setSelectedVideo(Pseudovideo v) {
            if (!items.contains(v)) {
                editPanel.metadataFieldPanel.clearFields();
                return;
            }

            selectionList.setSelectedValue(v, true);
        }

        public Pseudovideo getSelectedVideo() {
            return selectionList.getSelectedValue();
        }
    }

    class EditPanel extends JPanel {
        MetadataFieldPanel metadataFieldPanel;
        ButtonPanel buttonPanel;

        public EditPanel() {
            metadataFieldPanel = new MetadataFieldPanel();
            buttonPanel = new ButtonPanel();

            addComponents();
        }

        private void addComponents() {
            BoxLayout verticalLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            setLayout(verticalLayout);

            add(metadataFieldPanel);
            add(buttonPanel);
            add(Box.createVerticalStrut(5));

            setBorder(MyFrameTools.createMyTitledBorder("Editing Panel"));
        }
    }

    class ButtonPanel extends JPanel {
        JRadioButton sortByTitleButton;
        JRadioButton sortByViewsButton;
        JRadioButton sortByRatioButton;

        JButton addButton;
        JButton deleteButton;
        JButton loadButton;
        JButton applyButton;
        JButton saveButton;

        public ButtonPanel() {
            sortByTitleButton = new JRadioButton("Sort by Title");
            sortByViewsButton = new JRadioButton("Sort by Views");
            sortByRatioButton = new JRadioButton("Sort by Ratio");

            addButton = new JButton("Add");
            deleteButton = new JButton("Delete");
            loadButton = new JButton("Load");
            saveButton = new JButton("Save");
            applyButton = new JButton("Apply");

            addComponents();
            bindActions();
        }

        private void addComponents() {
            ButtonGroup group = new ButtonGroup();
            group.add(sortByViewsButton);
            group.add(sortByTitleButton);
            group.add(sortByRatioButton);
            sortByViewsButton.setSelected(true);

            BoxLayout verticalLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            setLayout(verticalLayout);
            add(MyFrameTools.getHorizontalContainer(false, sortByViewsButton, sortByTitleButton, sortByRatioButton));
            add(MyFrameTools.getHorizontalContainer(true, loadButton, addButton, deleteButton, applyButton, saveButton));
        }

        private void bindActions() {
            loadButton.addActionListener(e -> videoBase.loadFrom(filenamePrompt.getFilenameInput()));
            saveButton.addActionListener(e -> videoBase.saveTo(filenamePrompt.getFilenameInput()));

            addButton.addActionListener(e -> {
                Pseudovideo newVideo = newItemPrompt.getNewItem();
                videoBase.add(newVideo, true);
                videoSelectionPanel.setSelectedVideo(newVideo);
            });

            deleteButton.addActionListener(e -> {
                if (deletionPrompt.getConfirmation()) {
                    videoBase.delete(videoSelectionPanel.getSelectedVideo());
                }
            });

            applyButton.addActionListener(e ->
                videoSelectionPanel.getSelectedVideo().setMetadata(editPanel.metadataFieldPanel.iterator())
            );

            sortByViewsButton.addActionListener(e -> videoBase.setComparator(VideoComparators.VIEW_COMPARATOR.reversed()));
            sortByTitleButton.addActionListener(e -> videoBase.setComparator(VideoComparators.TITLE_COMPARATOR));
            sortByRatioButton.addActionListener(e -> videoBase.setComparator(VideoComparators.RATIO_COMPARATOR.reversed()));
        }
    }
}
