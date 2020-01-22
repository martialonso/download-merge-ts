package me.martiii.downloadmergets.screen;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    public Screen() {
        super("DownloadMergeTS");

        setLayout(new BorderLayout());

        ParametersPanel parametersPanel = new ParametersPanel();
        add(parametersPanel, BorderLayout.NORTH);

        OutputPanel outputPanel = new OutputPanel();
        add(outputPanel, BorderLayout.CENTER);

        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setVisible(true);
    }
}
