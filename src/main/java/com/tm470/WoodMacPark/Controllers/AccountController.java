package com.tm470.WoodMacPark.Controllers;

import com.tm470.WoodMacPark.Models.Account;
import com.tm470.WoodMacPark.Repositories.AccountNameRepository;
import com.tm470.WoodMacPark.Repositories.AccountIdRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountIdRepository accountRepository;

    @Autowired
    private AccountNameRepository accountNameRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Account> list() {
        return accountRepository.findAll();
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute Account account,
                               RedirectAttributes redirectAttributes)
    {
         accountRepository.saveAndFlush(account);
        redirectAttributes.addFlashAttribute("message", "Account successfully created.");

        return new ModelAndView("redirect:/new", "account",
                new Account());
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getById(@PathVariable int id)
    {
        return accountRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/name/{username}", method = RequestMethod.GET)
    public Account getByName(@PathVariable String username)
    {
        return accountNameRepository.findByName(username);
    }

    @RequestMapping(value = "/name/{username}", method = RequestMethod.PUT)
    public Account updateByName(@PathVariable String username, @RequestBody Account account)
    {
        Account existingAccount = accountNameRepository.findByName(username);
        BeanUtils.copyProperties(account, existingAccount);
        return accountNameRepository.saveAndFlush(existingAccount);
    }

    @RequestMapping(value = "/name/{username}", method = RequestMethod.DELETE)
    public Account deleteByName(@PathVariable String username, @RequestBody Account account)
    {
        Account existingAccount = accountNameRepository.findByName(username);
        accountNameRepository.delete(existingAccount);
        return existingAccount;
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
