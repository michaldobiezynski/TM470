package com.tm470.WoodMacPark.Configuration;

import com.tm470.WoodMacPark.Models.Booking;
import com.tm470.WoodMacPark.Models.BookingForToday;
import com.tm470.WoodMacPark.Models.Space;
import com.tm470.WoodMacPark.Models.WeeklyBooking;
import com.tm470.WoodMacPark.Repositories.BookingRepository;
import com.tm470.WoodMacPark.Repositories.BookingsForTodayRepository;
import com.tm470.WoodMacPark.Repositories.SpaceFixedRepository;
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

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SpaceFixedRepository spaceFixedRepository;

    @Autowired
    private BookingsForTodayRepository bookingsForTodayRepository;


    @Scheduled(cron = "1 0 0 ? * Fri")
    public void clearBookingsAtEndOfDay() {

        try {
            weeklyBookingRepository.deleteAll();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Scheduled(fixedDelay = 40000)
    public void moveBookingsToTodayTable() {

        List<Space> notFixedSpaces = spaceFixedRepository.findByFixedIsNot(true);

        if(notFixedSpaces.isEmpty()) {

            System.out.println("All spaces are fixed.");

        } else {

            for (Space space:notFixedSpaces) {

                if(bookingRepository.findBySpace(space.getId()) != null ){
                    Booking booking = bookingRepository.findBySpace(space.getId());

                    BookingForToday bookingForToday = new BookingForToday();

                    bookingForToday.setSpace(booking.getSpace());

                    bookingForToday.setUser(booking.getUser());

                    bookingsForTodayRepository.saveAndFlush(bookingForToday);

                    bookingRepository.delete(booking);

                    space.setBooked(false);

                    space.setUserId(0);

                    spaceFixedRepository.saveAndFlush(space);

                } else {

                    System.out.println("There is no booking for this space: " + space.getId());

                }
            }
        }
    }

    @Scheduled(fixedDelay = 50000)
    public void clearBookingsForTodayTable() {

        bookingsForTodayRepository.deleteAll();

    }

}
