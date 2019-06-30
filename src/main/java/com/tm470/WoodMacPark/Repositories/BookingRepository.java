package com.tm470.WoodMacPark.Repositories;

import com.tm470.WoodMacPark.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {


}
