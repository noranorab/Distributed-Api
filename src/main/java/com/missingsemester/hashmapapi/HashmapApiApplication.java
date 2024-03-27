package com.missingsemester.hashmapapi;

import com.missingsemester.hashmapapi.api.config.AppConfig;
import com.missingsemester.hashmapapi.api.service.ShardMonitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(AppConfig.class)
public class HashmapApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HashmapApiApplication.class, args);

    }

}
