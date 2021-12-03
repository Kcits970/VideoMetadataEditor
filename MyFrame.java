import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyFrame extends JFrame {
    StatsHandler statsHandler;
    VideoSelectionPanel videoSelectionPanel;
    DataEditorPanel dataEditorPanel;
    StatisticsPanel statsPanel;
    DetailedVideoListPanel detailedVideoListPanel;

    public MyFrame(StatsHandler s) {
        statsHandler = s;

        videoSelectionPanel = new VideoSelectionPanel();
        dataEditorPanel = new DataEditorPanel();
        statsPanel = new StatisticsPanel();
        detailedVideoListPanel = new DetailedVideoListPanel();

        addComponents();
        refreshComponents();
        configureSettings();
    }

    private void addComponents() {
        JPanel main = new JPanel();

        main.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5,5,5,5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        main.add(videoSelectionPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        main.add(dataEditorPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        main.add(statsPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        main.add(detailedVideoListPanel, constraints);

        setContentPane(MyWindowTools.getBorderSpacedPanel(main));
    }

    private void configureSettings() {
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Video Data Editor");
        setVisible(true);
    }

    void refreshComponents() {
        videoSelectionPanel.appendAllVideos(statsHandler.getVideos());
        statsPanel.writeStatistics(statsHandler);
        detailedVideoListPanel.refreshPanels(statsHandler.getVideos());
    }

    class VideoSelectionPanel extends JPanel {
        DefaultListModel<Video> items;
        JList<Video> selectionList;

        public VideoSelectionPanel() {
            items = new DefaultListModel();
            selectionList = new JList(items);
            selectionList.setFont(MyWindowTools.ARIAL_UNICODE_18);

            addSelectionList();
            bindActions();
        }

        private void appendAllVideos(List<Video> videos) {
            items.clear();
            for (Video v : videos)
                addNewVideo(v);
        }

        private void addNewVideo(Video v) {
            items.addElement(v);
        }

        private void addSelectionList() {
            JScrollPane scrollPane = new JScrollPane(selectionList
                    , ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
                    , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

            scrollPane.setPreferredSize(new Dimension(300, 0));

            setLayout(new BorderLayout());
            add(scrollPane);
            setBorder(MyWindowTools.createMyBorder("Video Selection Panel"));
        }

        private void bindActions() {
            selectionList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    dataEditorPanel.updateTextFields(selectionList.getSelectedValue());
                }
            });
        }

        public void setSelectedVideo(Video v) {
            selectionList.setSelectedValue(v, true);
        }

        public Video getSelectedVideo() {
            return selectionList.getSelectedValue();
        }
    }

    class DataEditorPanel extends JPanel {
        TextFieldPanel textFieldPanel;
        ButtonPanel buttonPanel;

        public DataEditorPanel() {
            textFieldPanel = new TextFieldPanel();
            buttonPanel = new ButtonPanel();

            addComponents();
        }

        private void addComponents() {
            BoxLayout verticalLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            setLayout(verticalLayout);
            add(textFieldPanel);
            add(buttonPanel);
            add(Box.createVerticalStrut(5));
            setBorder(MyWindowTools.createMyBorder("Editing Panel"));
        }

        public void updateTextFields(Video v) {
            textFieldPanel.setTextFieldValues(v);
        }

        public void updateVideo(Video v) {
            textFieldPanel.changeVideoAttributes(v);
        }
    }

    class ButtonPanel extends JPanel {
        JRadioButton sortByTitleButton;
        JRadioButton sortByViewsButton;
        JButton applyChangesButton;
        JButton saveToFileButton;

        public ButtonPanel() {
            sortByTitleButton = new JRadioButton("Sort by Title");
            sortByViewsButton = new JRadioButton("Sort by Views");
            applyChangesButton = new JButton("Apply Changes");
            saveToFileButton = new JButton("Save to File");

            BoxLayout verticalLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            setLayout(verticalLayout);
            addRadioButtons();
            addButtons();
            bindActions();
        }

        private void addRadioButtons() {
            ButtonGroup group = new ButtonGroup();
            group.add(sortByViewsButton);
            group.add(sortByTitleButton);
            sortByViewsButton.setSelected(true);

            JPanel radioButtonPanel = new JPanel();
            radioButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            radioButtonPanel.add(sortByViewsButton);
            radioButtonPanel.add(sortByTitleButton);

            add(radioButtonPanel);
        }

        private void addButtons() {
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            buttonPanel.add(applyChangesButton);
            buttonPanel.add(saveToFileButton);

            add(buttonPanel);
        }

        private void bindActions() {
            applyChangesButton.addActionListener(e -> {
                Video selectedVideo = videoSelectionPanel.getSelectedVideo();
                dataEditorPanel.updateVideo(selectedVideo);
                if (sortByViewsButton.isSelected())
                    statsHandler.sortBy(Comparators.VIEW_COMPARATOR);
                else
                    statsHandler.sortBy(Comparators.TITLE_COMPARATOR);

                refreshComponents();
                videoSelectionPanel.setSelectedVideo(selectedVideo);
            });
            saveToFileButton.addActionListener(e -> statsHandler.saveToFile());
        }
    }
}
