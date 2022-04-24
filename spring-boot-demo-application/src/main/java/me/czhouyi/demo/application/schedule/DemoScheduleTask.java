package me.czhouyi.demo.application.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class DemoScheduleTask {

    @Scheduled(cron = "0 0/15 * * * ?")
    void configureTasks() {
        log.info("demo schedule task starting...");
        long start = System.currentTimeMillis();
        log.info("demo schedule task end, cost={}ms", System.currentTimeMillis() - start);
    }

}