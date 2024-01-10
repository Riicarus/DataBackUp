package io.github.riicarus;

/**
 * @author Riicarus
 * @create 2024-1-9 12:18
 * @since 1.0.0
 */
public class DefaultFileManager implements FileManager {

    private final FileMetaMaster metaMaster = new FileMetaMaster();

    @Override
    public void backup(FileConfig config) {
        final FileMetaData metaData = metaMaster.getMetaData(config);

        if (metaData == null) {
            long size;
            if ((size = FileSaver.save(config.name, config.src, config.dst)) != -1)
                metaMaster.addMetaData(config, size);
            return;
        }

        if (
                metaData.permissionSet.contains(FileMetaData.Permission.R) ||
                        (!metaData.owner.equals(config.owner) && metaData.permissionSet.contains(FileMetaData.Permission.X))
        ) return;

        long size;
        if ((size = FileSaver.save(config.name, config.src, config.dst)) != -1) {
            metaMaster.addMetaData(config, size);
            metaData.lastUpdateTime = config.timestamp;
        }
    }

    @Override
    public void scheduledBackup(FileConfig config, long time) {
        Runnable runnable = () -> {
            while (true) {
                backup(config);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void reduce(FileConfig config) {
        final FileMetaData metaData = metaMaster.getMetaData(config);
        if (metaData == null) return;

        // shared file or is its owner
        if (metaData.permissionSet.contains(FileMetaData.Permission.X) || metaData.owner.equals(config.owner))
            FileReducer.reduce(metaData.path, config.dst);
        else System.out.println("Can not get file: " + config.name + " because the permission not permits.");
    }

    @Override
    public void listMetaData() {
        metaMaster.listMetaData();
    }
}
