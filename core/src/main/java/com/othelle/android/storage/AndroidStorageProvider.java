package com.othelle.android.storage;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static com.othelle.java.util.Strings.isEmpty;

/**
 * User: v.vlasov
 * Date: 1/11/12
 */
public abstract class AndroidStorageProvider {
    public static enum Folder {
        DATA("data"),
        BACKUP("backup"),
        EXPORT("export");

        private final String folderName;

        Folder(String folderName) {
            this.folderName = folderName;
        }

        String getFolderName() {
            return folderName;
        }
    }


    public String[] list(String saveFolder) throws IOException {
        return getStorageFile(saveFolder, "1.txt").getParentFile().list();
    }

    public File[] listFiles(String saveFolder) throws IOException {
        return getStorageFile(saveFolder, "1.txt").getParentFile().listFiles();
    }

    public File getStorageFile(Folder folder, String filename) throws IOException {
        return getStorageFile(folder.getFolderName(), filename);
    }

    public File getStorageFile(String saveFolder, String filename) throws IOException {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            StringBuilder builder = new StringBuilder();
            builder.append(Environment.getExternalStorageDirectory());
            builder.append("/");
            builder.append(getApplicationDataFolder());
            builder.append("/");
            builder.append(saveFolder);

            File folder = new File(builder.toString());
            if (!folder.exists()) {
                if (!folder.mkdirs()) {
                    throw new IOException("Unable to make dir: " + folder.getAbsolutePath());
                }
            }

            if (filename != null) {
                builder.append("/");
                builder.append(filename);
            }
            return new File(builder.toString());
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            throw new IOException("Storage is in Read-Only Mode");
        } else {
            throw new IOException("Storage is unavailable");
        }
    }


    public static final String DEFAULT_DATEFORMAT = "yyyyMMdd_hhmmss";
    private static DateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATEFORMAT);

    public static String generateDefaultName(String extension) {
        return generateDefaultName("", extension);
    }

    public static String generateDefaultName(String prefix, String extension) {

        StringBuilder builder = new StringBuilder(prefix.length() + extension.length() + DEFAULT_DATEFORMAT.length() + 2);
        if (!isEmpty(prefix))
            builder.append(prefix).append('_');

        String name = simpleDateFormat.format(System.currentTimeMillis());
        if (!isEmpty(extension)) {
            builder.append('.').append(extension);
        }
        return builder.toString();
    }

    public abstract String getApplicationDataFolder();
}
