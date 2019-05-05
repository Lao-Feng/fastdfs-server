package com.ca.fastdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @author 冯杰
 */
@Import(FdfsClientConfig.class)
// 解决jmx重复注册bean的问题

@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@SpringBootApplication
public class FastdfsStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastdfsStarterApplication.class, args);
    }

}
