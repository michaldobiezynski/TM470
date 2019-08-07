package com.tm470.WoodMacPark.Repositories;


import com.tm470.WoodMacPark.Models.WeeklyBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyBookingRepository extends JpaRepository<WeeklyBooking, Integer> {

    @Query(value = "SELECT * FROM WeeklyBookings b WHERE b.userId = ?1", nativeQuery = true)
    WeeklyBooking findByUser(@Param("user") int user);

}
