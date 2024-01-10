package io.github.riicarus;

/**
 * @author Riicarus
 * @create 2024-1-9 13:01
 * @since 1.0.0
 */
public class FileSaver {

    public static long save(String name, String src, String dst) {
        return FileCopier.copyDir(src, dst);
    }

}
