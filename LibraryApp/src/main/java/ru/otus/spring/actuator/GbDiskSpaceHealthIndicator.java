package ru.otus.spring.actuator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.system.DiskSpaceHealthIndicator;
import org.springframework.core.log.LogMessage;
import org.springframework.util.unit.DataSize;

import java.io.File;


public class GbDiskSpaceHealthIndicator extends DiskSpaceHealthIndicator {

    private static final Log logger = LogFactory.getLog(DiskSpaceHealthIndicator.class);
    private final File path;
    private final DataSize threshold;

    public GbDiskSpaceHealthIndicator(File path, DataSize threshold) {
        super(path, threshold);
        this.path = path;
        this.threshold = threshold;

    }

    // кастомизация - показ памяти не в байтах, а в гигабайтах
    @Override
    protected void doHealthCheck(Health.Builder builder) {
        long diskFreeInBytes = path.getUsableSpace();
        if (diskFreeInBytes >= threshold.toBytes()) {
            builder.up();
        } else {
            logger.warn(LogMessage.format("Free disk space below threshold. Available: %d gigabytes (threshold: %s)", diskFreeInBytes / 1024 / 1024 / 1024, this.threshold));
            builder.down();
        }

        builder.withDetail("total gb", this.path.getTotalSpace()  / 1024 / 1024 / 1024.00).withDetail("free gb", diskFreeInBytes / 1024 / 1024.00 / 1024).withDetail("threshold gb", this.threshold.toBytes() / 1024.00 / 1024 / 1024).withDetail("exists", this.path.exists());

    }
}
