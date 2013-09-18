package com.othelle.android.action.http;

import android.content.Context;
import com.othelle.android.action.Cancellable;
import com.othelle.android.action.ProgressObserver;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertThat;

/**
 * User: Vasily Vlasov
 * Date: 9/16/13
 */
@RunWith(RobolectricTestRunner.class)
public class DownloadActionWithProgressManualTest {

    @Test
    public void testDownloadOfLargeFile() throws IOException {
        Context context = Robolectric.getShadowApplication().getApplicationContext();
        String userHome = System.getProperty("user.home");
        String url = "http://traffic.libsyn.com/bizpod/BEP236-Luck-Idioms1.mp3";


        File fileToDownload = new File(userHome, "temp/BEP236-Luck-Idioms1.mp3".toLowerCase());
        DownloadActionWithProgress downloadActionWithProgress = new DownloadActionWithProgress(context, fileToDownload);
        downloadActionWithProgress.setProgressObserver(new ProgressObserver() {
            @Override
            public void setCancellable(Cancellable cancellable) {
            }

            @Override
            public void setProgress(int percentage) {
                System.out.println("Progress: " + percentage + "%");
            }

            @Override
            public void started() {
            }

            @Override
            public void cancelled() {
            }

            @Override
            public void completed() {
            }
        });
        downloadActionWithProgress.downloadWithProgress(url);

        assertThat(fileToDownload.exists(), Matchers.equalTo(true));
        assertThat(fileToDownload.getTotalSpace(), Matchers.greaterThan(1024l));


    }

}
