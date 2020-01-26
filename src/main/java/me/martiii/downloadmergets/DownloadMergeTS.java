package me.martiii.downloadmergets;

import me.martiii.downloadmergets.download.DownloadThread;
import me.martiii.downloadmergets.merge.MergeThread;
import me.martiii.downloadmergets.screen.Screen;

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
}
