package me.martiii.downloadmergets;

import me.martiii.downloadmergets.download.DownloadThread;
import me.martiii.downloadmergets.screen.Screen;

public class DownloadMergeTS {
    public static void main(String[] args) {
        new DownloadMergeTS();
    }

    private Screen screen;
    private DownloadThread downloadThread;

    public DownloadMergeTS() {
        screen = new Screen();
        downloadThread = new DownloadThread(this, (url, file) -> {
            log(url + " - Downloaded!");
        });
        downloadThread.start();
    }

    public void log(String msg) {
        screen.appendToLog(msg);
    }
}
