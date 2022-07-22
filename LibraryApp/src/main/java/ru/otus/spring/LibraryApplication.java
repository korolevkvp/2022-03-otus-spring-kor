package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.system.DiskSpaceHealthIndicatorProperties;
import org.springframework.boot.actuate.system.DiskSpaceHealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.otus.spring.actuator.GbDiskSpaceHealthIndicator;

import java.sql.SQLException;

@EnableAspectJAutoProxy
@EnableCircuitBreaker
@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) throws SQLException {

        Console.main(args);
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public DiskSpaceHealthIndicator diskSpaceHealthIndicator(DiskSpaceHealthIndicatorProperties properties) {
        return new GbDiskSpaceHealthIndicator(properties.getPath(), properties.getThreshold());
    }

}
