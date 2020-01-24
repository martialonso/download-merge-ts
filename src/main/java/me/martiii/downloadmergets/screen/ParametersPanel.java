package me.martiii.downloadmergets.screen;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ParametersPanel extends JPanel {
    public ParametersPanel() {
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
        GridBagConstraints rp = new GridBagConstraints();
        Insets defInsets = new Insets(2, 2, 2, 2);
        Insets extraInsets = new Insets(2, 20, 2, 2);
        rp.insets = defInsets;

        JLabel startLabel = new JLabel("Start: ");
        rp.gridx = 0;
        rp.gridy = 0;
        rangePanel.add(startLabel, rp);

        JSpinner startSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        ((JSpinner.DefaultEditor) startSpinner.getEditor()).getTextField().setColumns(4);
        rp.gridx = 1;
        rangePanel.add(startSpinner, rp);

        JLabel endLabel = new JLabel("End: ");
        rp.gridx = 2;
        rp.insets = extraInsets;
        rangePanel.add(endLabel, rp);

        JSpinner endSpinner = new JSpinner(new SpinnerNumberModel(100, 0, null, 1));
        ((JSpinner.DefaultEditor) endSpinner.getEditor()).getTextField().setColumns(4);
        rp.gridx = 3;
        rp.insets = defInsets;
        rangePanel.add(endSpinner, rp);

        JLabel stepLabel = new JLabel("Step: ");
        rp.gridx = 4;
        rp.insets = extraInsets;
        rangePanel.add(stepLabel, rp);

        JSpinner stepSpinner = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));
        ((JSpinner.DefaultEditor) stepSpinner.getEditor()).getTextField().setColumns(4);
        rp.gridx = 5;
        rp.insets = defInsets;
        rangePanel.add(stepSpinner, rp);

        JLabel digitsLabel = new JLabel("Digits: ");
        rp.gridx = 6;
        rp.insets = extraInsets;
        rangePanel.add(digitsLabel, rp);

        JSpinner digitSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        ((JSpinner.DefaultEditor) digitSpinner.getEditor()).getTextField().setColumns(4);
        rp.gridx = 7;
        rp.insets = defInsets;
        rangePanel.add(digitSpinner, rp);

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

        JButton startButton = new JButton("Download and merge");
        pc.fill = GridBagConstraints.NONE;
        pc.weightx = 0;
        pc.gridx = 0;
        pc.gridy = 3;
        pc.anchor = GridBagConstraints.LINE_END;
        pc.insets = new Insets(2, 2, 2, 2);
        add(startButton, pc);
    }
}
