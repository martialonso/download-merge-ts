package me.martiii.downloadmergets.screen;

import me.martiii.downloadmergets.DownloadMergeTS;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    private DownloadMergeTS downloadMergeTS;
    private OutputPanel outputPanel;

    public Screen(DownloadMergeTS downloadMergeTS) {
        super("DownloadMergeTS");
        this.downloadMergeTS = downloadMergeTS;

        setLayout(new BorderLayout());

        ParametersPanel parametersPanel = new ParametersPanel(downloadMergeTS);
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
