package io.github.riicarus;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Riicarus
 * @create 2024-1-9 12:50
 * @since 1.0.0
 */
public class FileMetaMaster {

    private final Set<FileMetaData> metaDataSet = new HashSet<>();

    public void addMetaData(FileConfig config, long size) {
        metaDataSet.add(new FileMetaData(config.name, config.dst, config.owner, size, config.timestamp, config.permissionSet));
    }

    public FileMetaData getMetaData(FileConfig config) {
        for (FileMetaData metaData : metaDataSet) {
            if (metaData.name.equals(config.name) && (config.owner == null || metaData.name.equals(config.owner))) {
                return metaData;
            }
        }

        return null;
    }

    public void listMetaData() {
        metaDataSet.forEach(System.out::println);
    }
}
