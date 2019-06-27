package com.tm470.WoodMacPark.Controllers;

import com.tm470.WoodMacPark.Models.Account;
import com.tm470.WoodMacPark.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Account> list() {
        return accountRepository.findAll();
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Account create(@RequestBody Account account)
    {
        return accountRepository.saveAndFlush(account);
    }


}
