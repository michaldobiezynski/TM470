package com.tm470.WoodMacPark.Repositories;


import com.tm470.WoodMacPark.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String role);

}