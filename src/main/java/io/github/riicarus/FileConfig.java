package io.github.riicarus;

import java.util.*;

/**
 * @author Riicarus
 * @create 2024-1-9 13:14
 * @since 1.0.0
 */
public class FileConfig {

    public final String name;
    public final String dst;
    public String src;
    public EnumSet<FileMetaData.Permission> permissionSet;
    public String owner;
    public final long timestamp;

    public FileConfig(String name, String dst) {
        this.name = name;
        this.dst = dst;
        this.timestamp = System.currentTimeMillis();
    }

    public FileConfig src(String src) {
        this.src = src;
        return this;
    }

    public FileConfig permissions(EnumSet<FileMetaData.Permission> permissionSet) {
        this.permissionSet = permissionSet;
        return this;
    }

    public FileConfig owner(String owner) {
        this.owner = owner;
        return this;
    }
}
