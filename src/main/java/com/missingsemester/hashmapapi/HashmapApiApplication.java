package com.missingsemester.hashmapapi;

import com.missingsemester.hashmapapi.api.config.AppConfig;
import com.missingsemester.hashmapapi.api.service.ShardMonitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@Import(AppConfig.class)
@EnableScheduling
public class HashmapApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HashmapApiApplication.class, args);

    }

}
