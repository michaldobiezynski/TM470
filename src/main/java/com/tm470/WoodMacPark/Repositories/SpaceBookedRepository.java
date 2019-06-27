package com.tm470.WoodMacPark.Repositories;

import com.tm470.WoodMacPark.Models.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceBookedRepository extends JpaRepository<Space, Boolean> {

    List<Space> findByBooked(@Param("Booked") boolean booked);
}
