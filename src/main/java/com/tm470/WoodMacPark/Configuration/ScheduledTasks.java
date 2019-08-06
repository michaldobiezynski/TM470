package com.tm470.WoodMacPark.Configuration;

import com.tm470.WoodMacPark.Models.WeeklyBooking;
import com.tm470.WoodMacPark.Repositories.WeeklyBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private WeeklyBookingRepository weeklyBookingRepository;

    @Scheduled(cron = "0 1 0 * * ?")
    public void clearBookingsAtEndOfDay() {

        try {

            weeklyBookingRepository.deleteAllByBookedThisWeek(true);

        } catch (Exception e) {

            System.out.println(e);

        }


    }

}
