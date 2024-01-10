package io.github.riicarus;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Riicarus
 * @create 2024-1-9 13:08
 * @since 1.0.0
 */
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static DefaultFileManager manager = new DefaultFileManager();

    public static void main(String[] args) {
        String line;
        while ((line = scanner.nextLine()) != null) {
            String[] parts = line.split(" ");

            switch (parts[0]) {
                // backup name src dst [owner] [permission1_permission2_...]
                case "backup" -> {
                    if (parts.length < 4) continue;
                    FileConfig config = new FileConfig(parts[1], parts[3]).src(parts[2]);
                    if (parts.length >= 5) {
                        config.owner(parts[4]);
                    }
                    if (parts.length >= 6) {
                        String[] permissions = parts[5].split("_");
                        Set<FileMetaData.Permission> permissionSet = new HashSet<>();
                        for (String permission : permissions) {
                            permissionSet.add(FileMetaData.Permission.valueOf(permission));
                        }
                        config.permissions(EnumSet.copyOf(permissionSet));
                    }
                    manager.backup(config);

                    System.out.println("backup succeeded.");
                }
                case "reduce" -> {
                    if (parts.length < 3) continue;
                    FileConfig config = new FileConfig(parts[1], parts[2]);
                    if (parts.length == 4) {
                        config.owner(parts[3]);
                    }
                    manager.reduce(config);

                    System.out.println("reduce succeeded.");
                }
                case "timed_backup" -> {
                    long time = 0;
                    if (parts.length < 5) continue;
                    FileConfig config = new FileConfig(parts[1], parts[3]).src(parts[2]);
                    if (parts.length >= 6) {
                        config.owner(parts[4]);
                        time = Long.parseLong(parts[5]);
                    }
                    if (parts.length >= 7) {
                        String[] permissions = parts[5].split("_");
                        Set<FileMetaData.Permission> permissionSet = new HashSet<>();
                        for (String permission : permissions) {
                            permissionSet.add(FileMetaData.Permission.valueOf(permission));
                        }
                        config.permissions(EnumSet.copyOf(permissionSet));
                        time = Long.parseLong(parts[6]);
                    }
                    manager.scheduledBackup(config, time);

                    System.out.println("timed backup succeeded.");
                }
                case "list_meta" -> manager.listMetaData();
            }
        }
    }

}
