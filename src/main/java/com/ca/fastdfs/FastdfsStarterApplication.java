package com.ca.fastdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author 冯杰
 */
@SpringBootApplication
@Import(FdfsClientConfig.class)
public class FastdfsStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastdfsStarterApplication.class, args);
    }

}
