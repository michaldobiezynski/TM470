package com.tm470.WoodMacPark.Controllers;

import com.tm470.WoodMacPark.Models.Account;
import com.tm470.WoodMacPark.Repositories.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account get(@PathVariable int id)
    {
        return accountRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Account update(@PathVariable int id, @RequestBody Account account)
    {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(account, existingAccount);
        return accountRepository.saveAndFlush(existingAccount);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Account delete(@PathVariable int id)
    {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        accountRepository.delete(existingAccount);
        return existingAccount;
    }



}
