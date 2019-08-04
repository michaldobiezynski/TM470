package com.tm470.WoodMacPark.Repositories;

import com.tm470.WoodMacPark.Models.Account;
import com.tm470.WoodMacPark.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "SELECT * FROM Bookings b WHERE b.user = ?1", nativeQuery = true)
    Booking findByUser(@Param("user") int user);

    @Query(value = "SELECT * FROM Bookings b WHERE b.space = ?1", nativeQuery = true)
    Booking findBySpace(@Param("space") int space);

}
