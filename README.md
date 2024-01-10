# Doc

## 文件备份

```shell
backup <fileName> <srcPath> <dstPath> [<ownerName>] [<permission>...]

backup backupFile D:\tmp\BigDataExp D:\tmp\BigDataExp_Backup riicarus X_WR
```

## 文件还原

```shell
reduce <fileName> <dstPath> [<ownerName>]

reduce backupFile D:\tmp\BigDataExp_Reduce
```

## 元数据列表

```shell
list_meta
```

## 定时备份

```shell
timed_backup <fileName> <srcPath> <dstPath> [<onwerName>] [<permission>...] <time_interval>

timed_backup timedBackupFile D:\tmp\BigDataExp D:\tmp\BigDataExp_TimedBackup riicarus 1000
```

## 构建

使用 Maven `package` 构建 Jar 包, 构建好后, 在 Jar 的 META_INF 中添加启动类: `io.github.riicarus.Main`.

## 运行

```shell
java -jar DataBackUp-version.jar
```