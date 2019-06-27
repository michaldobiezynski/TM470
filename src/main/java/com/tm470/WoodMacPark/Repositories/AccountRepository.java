package com.tm470.WoodMacPark.Repositories;

import com.tm470.WoodMacPark.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
