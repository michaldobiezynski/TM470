package com.tm470.WoodMacPark.Repositories;

import com.tm470.WoodMacPark.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountNameRepository extends JpaRepository<Account, String> {

    @Query(value = "SELECT * FROM Users u WHERE u.username = ?1", nativeQuery = true)
    Account findByName(@Param("username") String username);

}

