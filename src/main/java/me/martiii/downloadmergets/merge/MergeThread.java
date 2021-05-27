package me.martiii.downloadmergets.merge;

import me.martiii.downloadmergets.DownloadMergeTS;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MergeThread extends Thread {
    private DownloadMergeTS downloadMergeTS;
    private Callback callback;
    private String lastUrl;
    private List<File> files;
    private String output;

    public MergeThread(DownloadMergeTS downloadMergeTS, Callback callback) {
        this.downloadMergeTS = downloadMergeTS;
        this.callback = callback;
    }

    @Override
    public void run() {
        while (true) {
            if (lastUrl != null) {
                try {
                    File listFile = new File("list.txt");
                    PrintWriter out = new PrintWriter(listFile);
                    for (File src : files) {
                        out.println("file " + "'" + src.getPath().replace("\\", "/") + "'");
                    }
                    out.flush();
                    out.close();

                    downloadMergeTS.log("Merging downloaded videos...");
                    FFmpeg ffmpeg = new FFmpeg("DownloadMergeTS/bin/ffmpeg.exe");
                    FFmpegBuilder builder = new FFmpegBuilder()
                            .addExtraArgs("-safe")
                            .addExtraArgs("0")
                            .setInput(listFile.getAbsolutePath())
                            .setFormat("concat")
                            .addOutput(output)
                            .setAudioCodec("copy")
                            .setVideoCodec("copy")
                            .done();

                    FFmpegExecutor executor = new FFmpegExecutor(ffmpeg);
                    executor.createJob(builder).run();

                    files.forEach(file -> {
                        if (file.exists()) {
                            file.delete();
                        }
                    });

                    listFile.delete();

                    callback.merged(output);

                } catch (Exception e) {
                    downloadMergeTS.log(e.toString());
                }
            }
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void downloaded(String url, File file) {
        files.add(file);
        if (url.equals(lastUrl)) {
            notify();
        }
    }

    public void merge(String lastUrl, String output) {
        this.lastUrl = lastUrl;
        this.output = output;
        files = new ArrayList<>();
    }

    public void cancel() {
        this.lastUrl = null;
        this.output = null;
        files = new ArrayList<>();
    }

    public interface Callback {
        void merged(String output);
    }
}
