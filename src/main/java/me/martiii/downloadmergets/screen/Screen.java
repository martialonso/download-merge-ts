package me.martiii.downloadmergets.screen;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    private OutputPanel outputPanel;

    public Screen() {
        super("DownloadMergeTS");

        setLayout(new BorderLayout());

        ParametersPanel parametersPanel = new ParametersPanel();
        add(parametersPanel, BorderLayout.NORTH);

        outputPanel = new OutputPanel();
        add(outputPanel, BorderLayout.CENTER);

        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 400);
        setMinimumSize(new Dimension(520, 400));
        setVisible(true);
    }

    public void appendToLog(String msg) {
        outputPanel.appendToLog(msg);
    }
}
