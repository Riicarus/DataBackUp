package io.github.riicarus;

import java.util.EnumSet;
import java.util.UUID;

/**
 * @author Riicarus
 * @create 2024-1-9 12:51
 * @since 1.0.0
 */
public class FileMetaData {

    public enum Permission {
        W, R, WR,
        X, Y
    }

    public String name;
    public String id;
    public String path;
    public long size;
    public String owner;
    public long lastUpdateTime;
    public EnumSet<Permission> permissionSet;

    public FileMetaData(String name, String path, String owner, long size, long lastUpdateTime, EnumSet<Permission> permissionSet) {
        this.name = name;

        if (owner == null) this.owner = "public";
        else this.owner = owner;

        this.lastUpdateTime = lastUpdateTime;

        if (permissionSet == null || permissionSet.isEmpty())
            this.permissionSet = EnumSet.of(Permission.WR, Permission.X);
        else this.permissionSet = permissionSet;

        this.id = UUID.randomUUID().toString();
        this.path = path;
        this.size = size;
    }

    @Override
    public String toString() {
        return "[" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", path='" + path + '\'' +
                ", size='" + size + '\'' +
                ", owner='" + owner + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", permissionSet=" + permissionSet +
                ']';
    }
}
