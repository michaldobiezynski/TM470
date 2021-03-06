package com.tm470.WoodMacPark.Repositories;

import com.tm470.WoodMacPark.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountIdRepository extends JpaRepository<Account, Integer> {

}
