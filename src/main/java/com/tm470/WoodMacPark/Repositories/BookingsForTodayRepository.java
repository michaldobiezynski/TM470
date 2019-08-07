package com.tm470.WoodMacPark.Repositories;

import com.tm470.WoodMacPark.Models.Booking;
import com.tm470.WoodMacPark.Models.BookingForToday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsForTodayRepository extends
        JpaRepository<BookingForToday, Integer> {


    @Query(value = "SELECT * FROM bookingsfortoday b WHERE b.user = ?1", nativeQuery = true)
    BookingForToday findByUser(@Param("user") int user);

    @Query(value = "SELECT * FROM bookingsfortoday b WHERE b.space = ?1", nativeQuery = true)
    BookingForToday findBySpace(@Param("space") int space);


}
