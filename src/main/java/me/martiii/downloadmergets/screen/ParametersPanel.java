package me.martiii.downloadmergets.screen;

import me.martiii.downloadmergets.DownloadMergeTS;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ParametersPanel extends JPanel {
    private DownloadMergeTS downloadMergeTS;
    private JButton startButton;

    public ParametersPanel(DownloadMergeTS downloadMergeTS) {
        this.downloadMergeTS = downloadMergeTS;
        setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),
                new CompoundBorder(new TitledBorder("Parameters"), new EmptyBorder(5, 5, 5, 5))));
        setLayout(new GridBagLayout());

        GridBagConstraints pc = new GridBagConstraints();
        pc.fill = GridBagConstraints.HORIZONTAL;

        JPanel urlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints uc = new GridBagConstraints();

        uc.fill = GridBagConstraints.HORIZONTAL;
        uc.insets = new Insets(2, 2, 2, 2);

        JLabel urlLabel = new JLabel("URL: ");
        uc.gridx = 0;
        uc.gridy = 0;
        urlPanel.add(urlLabel, uc);

        JTextField urlTextField = new JTextField();
        uc.weightx = 1;
        uc.gridx = 1;
        urlPanel.add(urlTextField, uc);

        pc.weightx = 1;
        pc.gridx = 0;
        pc.gridy = 0;
        add(urlPanel, pc);

        JPanel rangePanel = new JPanel(new GridBagLayout());
        GridBagConstraints rc = new GridBagConstraints();
        Insets defInsets = new Insets(2, 2, 2, 2);
        Insets extraInsets = new Insets(2, 20, 2, 2);
        rc.insets = defInsets;
        rc.weightx = 1;

        JLabel startLabel = new JLabel("Start: ");
        rc.gridx = 0;
        rc.gridy = 0;
        rangePanel.add(startLabel, rc);

        JSpinner startSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        ((JSpinner.DefaultEditor) startSpinner.getEditor()).getTextField().setColumns(4);
        rc.gridx = 1;
        rangePanel.add(startSpinner, rc);

        JLabel endLabel = new JLabel("End: ");
        rc.gridx = 2;
        rc.insets = extraInsets;
        rangePanel.add(endLabel, rc);

        JSpinner endSpinner = new JSpinner(new SpinnerNumberModel(100, 0, null, 1));
        ((JSpinner.DefaultEditor) endSpinner.getEditor()).getTextField().setColumns(4);
        rc.gridx = 3;
        rc.insets = defInsets;
        rangePanel.add(endSpinner, rc);

        JLabel stepLabel = new JLabel("Step: ");
        rc.gridx = 4;
        rc.insets = extraInsets;
        rangePanel.add(stepLabel, rc);

        JSpinner stepSpinner = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));
        ((JSpinner.DefaultEditor) stepSpinner.getEditor()).getTextField().setColumns(4);
        rc.gridx = 5;
        rc.insets = defInsets;
        rangePanel.add(stepSpinner, rc);

        JLabel formatLabel = new JLabel("Format: ");
        rc.gridx = 6;
        rc.insets = extraInsets;
        rangePanel.add(formatLabel, rc);

        JTextField formatTextField = new JTextField("%01d", 4);
        rc.gridx = 7;
        rc.insets = defInsets;
        rangePanel.add(formatTextField, rc);

        pc.fill = GridBagConstraints.NONE;
        pc.weightx = 0;
        pc.gridy = 1;
        pc.anchor = GridBagConstraints.LINE_START;
        add(rangePanel, pc);

        JPanel outputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints oc = new GridBagConstraints();

        oc.fill = GridBagConstraints.HORIZONTAL;
        oc.insets = new Insets(2, 2, 2, 2);

        JLabel outputLabel = new JLabel("Output: ");
        oc.gridx = 0;
        oc.gridy = 0;
        outputPanel.add(outputLabel, oc);

        JTextField outputTextField = new JTextField();
        oc.weightx = 1;
        oc.gridx = 1;
        outputPanel.add(outputTextField, oc);

        JButton browseButton = new JButton("Browse");
        oc.gridx = 2;
        oc.weightx = 0;
        outputPanel.add(browseButton, oc);

        pc.fill = GridBagConstraints.HORIZONTAL;
        pc.weightx = 1;
        pc.gridx = 0;
        pc.gridy = 2;
        add(outputPanel, pc);

        startButton = new JButton("Download and merge");
        startButton.setEnabled(false);
        startButton.addActionListener(actionEvent -> {
            if (!urlTextField.getText().isEmpty()) {
                if (!outputTextField.getText().isEmpty()) {
                    startButton.setEnabled(false);
                    downloadMergeTS.downloadAndMerge(urlTextField.getText(), (int) startSpinner.getValue(),
                            (int) endSpinner.getValue(), (int) stepSpinner.getValue(), formatTextField.getText(),
                            outputTextField.getText());
                } else {
                    JOptionPane.showMessageDialog(downloadMergeTS.getScreen(), "Output cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(downloadMergeTS.getScreen(), "URL cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        pc.fill = GridBagConstraints.NONE;
        pc.weightx = 0;
        pc.gridx = 0;
        pc.gridy = 3;
        pc.anchor = GridBagConstraints.LINE_END;
        pc.insets = new Insets(2, 2, 2, 2);
        add(startButton, pc);
    }

    public void enableStartButton() {
        startButton.setEnabled(true);
    }
}
