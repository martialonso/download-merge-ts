package me.martiii.downloadmergets.download;

import me.martiii.downloadmergets.DownloadMergeTS;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadThread extends Thread {
    private DownloadMergeTS downloadMergeTS;
    private volatile boolean closed;
    private List<String> toDownload;
    private Callback callback;
    private File downloadDir;
    private Pattern fileNamePattern = Pattern.compile("(?<=/)[^/?]+(?=(\\?.*)*/*$)");

    public DownloadThread(DownloadMergeTS downloadMergeTS, Callback callback) {
        this.downloadMergeTS = downloadMergeTS;
        toDownload = new ArrayList<>();
        this.callback = callback;
        downloadDir = new File("download");
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }
    }

    @Override
    public void run() {
        while (!closed) {
            String url = null;
            synchronized (this) {
                if (!toDownload.isEmpty()) {
                    url = toDownload.remove(0);
                }
            }
            if (url != null) {
                try {
                    InputStream in = new URL(url).openStream();
                    Matcher matcher = fileNamePattern.matcher(url);
                    if (matcher.find()) {
                        File target = new File(downloadDir, matcher.group());
                        Files.copy(in, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        in.close();
                        callback.downloaded(url, target);
                    } else {
                        downloadMergeTS.log("Error getting url file name.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public synchronized void download(String url) {
        boolean waiting = toDownload.size() == 0;
        toDownload.add(url);
        if (waiting) {
            notify();
        }
    }

    public void close() {
        closed = true;
    }

    public interface Callback {
        void downloaded(String url, File file);
    }
}
