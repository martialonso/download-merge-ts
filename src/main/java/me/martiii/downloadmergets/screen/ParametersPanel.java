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
        rangePanel.add(endLabel, rp);

        JSpinner endSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        rp.gridx = 3;
        rangePanel.add(endSpinner, rp);

        JLabel stepLabel = new JLabel("Step: ");
        rp.gridx = 4;
        rangePanel.add(stepLabel, rp);

        JSpinner stepSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        rp.gridx = 5;
        rangePanel.add(stepSpinner, rp);

        pc.weightx = 0;
        pc.gridy = 1;
        add(rangePanel, pc);

    }
}
