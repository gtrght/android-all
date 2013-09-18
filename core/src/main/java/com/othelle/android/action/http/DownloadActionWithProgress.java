package com.othelle.android.action.http;

import android.content.Context;
import com.othelle.android.action.AsyncAction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: Vasily Vlasov
 * Date: 9/16/13
 */
public class DownloadActionWithProgress extends AsyncAction<String, File> {
    private File pathToStore;

    public DownloadActionWithProgress(Context context, File pathToStore) {
        super(context);
        this.pathToStore = pathToStore;
    }

    @Override
    protected File doInBackground(String urlToDownload) throws Exception {
        return downloadWithProgress(urlToDownload);
    }

    public File downloadWithProgress(String urlToDownload) throws IOException {
        URL url = new URL(urlToDownload);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);

        urlConnection.connect();

        File file = pathToStore;
        FileOutputStream fileOutput = new FileOutputStream(file);

        InputStream inputStream = urlConnection.getInputStream();

        int totalSize = urlConnection.getContentLength();
        int downloadedSize = 0;

        byte[] buffer = new byte[1024];
        int bufferLength;

        while ((bufferLength = inputStream.read(buffer)) > 0) {
            fileOutput.write(buffer, 0, bufferLength);
            downloadedSize += bufferLength;
            if (progressObserver != null) {
                progressObserver.setProgress((100 * downloadedSize) / totalSize);
            }

        }
        //close the output stream when done
        fileOutput.close();
        return file;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }
}
