package me.martiii.downloadmergets;

import me.martiii.downloadmergets.download.DownloadThread;
import me.martiii.downloadmergets.merge.MergeThread;
import me.martiii.downloadmergets.screen.Screen;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class DownloadMergeTS {
    public static void main(String[] args) {
        new DownloadMergeTS();
    }

    private Screen screen;
    private DownloadThread downloadThread;
    private MergeThread mergeThread;

    public DownloadMergeTS() {
        screen = new Screen(this);
        mergeThread = new MergeThread(this, output -> {
            log("Video successfully downloaded and merged!");
            log("Output: " + output);
            screen.getParametersPanel().enableStartButton();
        });
        mergeThread.start();

        downloadThread = new DownloadThread(this, (url, file) -> {
            log(url + " - Downloaded!");
            mergeThread.downloaded(url, file);
        });
        downloadThread.start();

        File ffmpegBin = new File("DownloadMergeTS/bin/ffmpeg.exe");
        if (!ffmpegBin.exists()) {
            ffmpegBin.getParentFile().mkdirs();
            new Thread(() -> {
                try {
                    log("Extracting FFmpeg binaries...");
                    InputStream in = DownloadMergeTS.class.getResourceAsStream("/bin/ffmpeg.exe");
                    Files.copy(in, ffmpegBin.getAbsoluteFile().toPath());
                    in.close();
                    log("FFmpeg binaries extracted.");
                    SwingUtilities.invokeLater(() -> screen.getParametersPanel().enableStartButton());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } else {
            SwingUtilities.invokeLater(() -> screen.getParametersPanel().enableStartButton());
        }
    }

    public void log(String msg) {
        screen.getOutputPanel().appendToLog(msg);
    }

    public Screen getScreen() {
        return screen;
    }

    public void downloadAndMerge(String url, int start, int end, int step, String format, String output) {
        log("Downloading video files...");
        for (int i = start; i <= end; i += step) {
            String num = String.format(format, i);
            downloadThread.download(url.replace("{num}", num));
        }
        mergeThread.merge(url.replace("{num}", String.format(format, end)), output.replace("\\", "/"));
    }

    public void cancelDownloadAndMerge() {
        log("Download and merge cancelled due to an error.");
        downloadThread.cancel();
        mergeThread.cancel();
        SwingUtilities.invokeLater(() -> screen.getParametersPanel().enableStartButton());
    }
}
