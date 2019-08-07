package com.tm470.WoodMacPark.Repositories;

import com.tm470.WoodMacPark.Models.BookingForToday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsForTodayRepository extends
        JpaRepository<BookingForToday, Integer> {


        }
