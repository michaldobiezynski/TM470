package com.tm470.WoodMacPark.Repositories;

import com.tm470.WoodMacPark.Models.WeeklyBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyBookingRepository extends JpaRepository<WeeklyBooking, Integer> {
}
