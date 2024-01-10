package io.github.riicarus;

/**
 * @author Riicarus
 * @create 2024-1-9 12:16
 * @since 1.0.0
 */
public interface FileManager {

    void backup(FileConfig config);

    void scheduledBackup(FileConfig config, long time);

    void reduce(FileConfig config);

    void listMetaData();

}
