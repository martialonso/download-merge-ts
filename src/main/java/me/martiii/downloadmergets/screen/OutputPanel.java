package me.martiii.downloadmergets.screen;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class OutputPanel extends JPanel {
    public OutputPanel() {
        setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),
                new CompoundBorder(new TitledBorder("Output"), new EmptyBorder(5, 5, 5, 5))));
        setLayout(new BorderLayout());

        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }
}
