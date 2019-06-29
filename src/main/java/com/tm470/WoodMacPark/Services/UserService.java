package com.tm470.WoodMacPark.Services;

import com.tm470.WoodMacPark.Models.Account;
import com.tm470.WoodMacPark.Models.Role;
import com.tm470.WoodMacPark.Repositories.AccountNameRepository;
import com.tm470.WoodMacPark.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

    @Service("userService")
    public class UserService {

        private AccountNameRepository accountNameRepository;
        private RoleRepository roleRepository;


        @Autowired
        public UserService(AccountNameRepository accountNameRepository,
                           RoleRepository roleRepository) {
            this.accountNameRepository = accountNameRepository;
            this.roleRepository = roleRepository;
        }

        public Account findByName(String username) {
            return accountNameRepository.findByName(username);
        }

        public void saveUser(Account account) {
            account.setPassword(account.getPassword());
            Role userRole = roleRepository.findByName("Admin");
            account.setRole(userRole.toString());
            accountNameRepository.save(account);
        }

    }

