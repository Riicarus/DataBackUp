package io.github.riicarus;

/**
 * @author Riicarus
 * @create 2024-1-9 13:02
 * @since 1.0.0
 */
public class FileReducer {

    public static void reduce(String src, String dst) {
        FileCopier.copyDir(src, dst);
    }

}
