package com.tm470.WoodMacPark.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTasks {

    @Scheduled(cron = "0 1 24 * * ?")
    public void clearBookingsAtEndOfDay() {

        System.out.println("Testing scheduled cron job");
    }

}
