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
        screen = new Screen(this);
        downloadThread = new DownloadThread(this, (url, file) -> {
            log(url + " - Downloaded!");
        });
        downloadThread.start();
    }

    public void log(String msg) {
        screen.appendToLog(msg);
    }

    public Screen getScreen() {
        return screen;
    }

    public void downloadAndMerge(String url, int start, int end, int step, String format, String output) {
        for (int i = start; i <= end; i += step) {
            String num = String.format(format, i);
            downloadThread.download(url.replace("{num}", num));
        }
    }
}
