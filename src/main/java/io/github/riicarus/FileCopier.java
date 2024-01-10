package io.github.riicarus;

import java.io.*;

/**
 * @author Riicarus
 * @create 2024-1-9 12:19
 * @since 1.0.0
 */
public class FileCopier {

    public static long copyDir(String src, String dst) {
        long size = 0;
        File srcFile = new File(src);
        File dstFile = new File(dst);

        // if file is not directory, may create parent dir file first.
        if (srcFile.isFile()) {
            File parentFile = dstFile.getParentFile();
            if (!parentFile.exists())
                if (!parentFile.mkdirs()) {
                    System.out.println("Can not create dir, name: " + dst);
                    return -1;
                }

            copyFile(src, dst);
            return srcFile.length();
        }

        // if file is directory, check if dst file dir exists.
        if (!dstFile.exists())
            if (!dstFile.mkdirs())
                throw new IllegalStateException("Can not create dir, name: " + dst);

        File[] srcFiles = srcFile.listFiles();
        if (srcFiles != null) {
            for (File file : srcFiles) {
                if (file.isDirectory()) {
                    long curSize = copyDir(src + File.separator + file.getName(), dst + File.separator + file.getName());
                    if (curSize == -1) return -1;
                    size += curSize;
                } else if (file.isFile()) {
                    long curSize = copyFile(src + File.separator + file.getName(), dst + File.separator + file.getName());
                    if (curSize == -1) return -1;
                    size += curSize;
                }
            }
        }

        return size;
    }

    private static long copyFile(String src, String dst) {
        long size = 0;
        try (
                InputStream is = new FileInputStream(src);
                OutputStream os = new FileOutputStream(dst)
        ) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
                size += len;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return size;
    }
}
