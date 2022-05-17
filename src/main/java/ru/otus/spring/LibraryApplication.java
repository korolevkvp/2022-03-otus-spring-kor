package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.system.DiskSpaceHealthIndicatorProperties;
import org.springframework.boot.actuate.system.DiskSpaceHealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.otus.spring.actuator.GbDiskSpaceHealthIndicator;

import java.sql.SQLException;

@EnableAspectJAutoProxy
@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) throws SQLException {

        // Console.main(args);
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public DiskSpaceHealthIndicator diskSpaceHealthIndicator(DiskSpaceHealthIndicatorProperties properties) {
        return new GbDiskSpaceHealthIndicator(properties.getPath(), properties.getThreshold());
    }

}
