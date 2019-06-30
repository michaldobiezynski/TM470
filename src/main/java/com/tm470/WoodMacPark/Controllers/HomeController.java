package com.tm470.WoodMacPark.Controllers;

import com.tm470.WoodMacPark.Models.Account;
import com.tm470.WoodMacPark.Models.AddUserModel;
import com.tm470.WoodMacPark.Models.AddUserModelValidator;
import com.tm470.WoodMacPark.Models.Space;
import com.tm470.WoodMacPark.Repositories.AccountIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private AccountIdRepository accountIdRepository;

    @RequestMapping(value = {"/", "/home"})
    public ModelAndView home() {
        Map<String, Object> model = new HashMap<String,Object>();
        model.put("username", "Michal");
        model.put("id", 173);
        return new ModelAndView("homepage", "model", model);

    }

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

//put this back in account controller class
    @RequestMapping(value = "/new")
    public ModelAndView create()
    {
        return new ModelAndView("createAccount","account", new Account());
    }



    //put this back in space controller class
    @RequestMapping(value = "/newS", method=RequestMethod.GET)
    public ModelAndView createS(Model model)
    {

        List<Account> allAcc =  accountIdRepository.findAll();

        model.addAttribute("allAcc", allAcc);

        return new ModelAndView("createSpace","space",new Space());
    }



    @RequestMapping("/profile")
    public ModelAndView viewProfile() {
        Map<String, Object> model = new HashMap<String,Object>();
        model.put("title","Mr");
        model.put("firstName", "Michal");
        model.put("surname", "Dobiezynski");
        model.put("dateOfBirth", new GregorianCalendar(2006,3,9).getTime());
        model.put("description", "a <string>fantastic</strong> Java Programmer");

        List<String> languages = new ArrayList<String>();
        languages.add("English");
        languages.add("French");
        languages.add("Spanish");
        languages.add("Danish");

        model.put("languages", languages);

        model.put("color", "#ccc");

        return new ModelAndView("profile", "model", model);
    }

    @RequestMapping("/addUser")
    public ModelAndView addUser() {

        return new ModelAndView("newUser", "addUserModel", new AddUserModel());
    }

    @RequestMapping(value="/saveUser", method= RequestMethod.POST)
    public String saveUser(@Valid AddUserModel addUserModel, BindingResult bindingResult) {
        new AddUserModelValidator().validate(addUserModel, bindingResult);
        if (bindingResult.hasErrors()) {
            return "newUser";
        }
        return "userAdded";
    }

}
