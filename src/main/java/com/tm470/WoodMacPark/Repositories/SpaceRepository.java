package com.tm470.WoodMacPark.Repositories;

import com.tm470.WoodMacPark.Models.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Integer> {
}
