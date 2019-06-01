package fastdfs;

/**
 * TODO
 *
 * @Author: FengJie
 * @Date: 2019/5/6 9:41
 */

import com.ca.fastdfs.FastdfsStarterApplication;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = FastdfsStarterApplication.class)
public class FastdfsTest {
    @Autowired
    private FastFileStorageClient client;

    @Test
    public void upload() throws FileNotFoundException {
        File file = new File("C:\\Users\\FengJie\\Desktop\\a.txt");
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = client.uploadFile(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), null);
        // 带分组的路径
        System.out.println("http://192.168.20.41:8888/" + storePath.getFullPath());
    }
}
