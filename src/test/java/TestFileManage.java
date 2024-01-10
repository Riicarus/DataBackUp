import io.github.riicarus.DefaultFileManager;
import io.github.riicarus.FileConfig;
import io.github.riicarus.FileMetaData;
import io.github.riicarus.FileSaver;
import org.junit.Test;

import java.util.EnumSet;

/**
 * @author Riicarus
 * @create 2024-1-9 12:47
 * @since 1.0.0
 */
public class TestFileManage {

    @Test
    public void testFileCopy() {
        FileSaver.save("file", "D:\\tmp\\BigDataExp", "D:\\tmp\\BigDataExp_Copy");
        FileSaver.save("file", "D:\\tmp\\BigDataExp\\技术报告.md", "D:\\tmp\\BigDataExp_Copy_single\\技术报告.md");
    }

    DefaultFileManager manager = new DefaultFileManager();
    FileConfig backUpConfig = new FileConfig("file", "D:\\tmp\\BigDataExp_Backup")
            .src("D:\\tmp\\BigDataExp")
            .owner("riicarus")
            .permissions(EnumSet.of(FileMetaData.Permission.WR, FileMetaData.Permission.Y));
    FileConfig reduceConfig = new FileConfig("file", "D:\\tmp\\BigDataExp_Reduce")
            .owner("riicarus");

    @Test
    public void testFileReduce() {
        manager.backup(backUpConfig);
        manager.reduce(reduceConfig);
        manager.listMetaData();
    }

}
