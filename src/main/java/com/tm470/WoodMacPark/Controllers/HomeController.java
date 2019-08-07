package com.tm470.WoodMacPark.Controllers;

import com.tm470.WoodMacPark.Models.*;
import com.tm470.WoodMacPark.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private AccountIdRepository accountIdRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private SpaceBookedRepository spaceBookedRepository;

    @Autowired
    private SpaceFixedRepository spaceFixedRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingsForTodayRepository bookingsForTodayRepository;


    @RequestMapping(value = {"/", "/home"})
    public ModelAndView home() {
        Map<String, Object> model = new HashMap<String,Object>();
        model.put("username", "Michal");
        model.put("id", 2);
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

    //put this back in space controller class
    @RequestMapping(value = "/ListAcc", method=RequestMethod.GET)
    public String ListAcc(Model model)
    {

        List<Account> allAcc =  accountIdRepository.findAll();

        model.addAttribute("allAcc", allAcc);

        return ("listAccounts");
    }

    @RequestMapping(value = "/ListSpc", method = RequestMethod.GET)
    public String ListSpc(Model model) {

        List<Space> allSpc = spaceRepository.findAll();

        model.addAttribute("allSpc", allSpc);

        return ("listSpaces");

    }

    @RequestMapping(value = "/ListBSpc", method = RequestMethod.GET)
    public String listBooked(Model model) {

        List<Space> allBSpc = spaceBookedRepository.findByBooked(true);

        model.addAttribute("allBSpc", allBSpc);

        return ("listBookedSpaces");

    }

    @RequestMapping(value = "/FreeSpaces", method = RequestMethod.GET)
    public String listFree(Model model) {

        List<Space> allFreeSpc = spaceBookedRepository.findByBooked(false);

        model.addAttribute("allFreeSpc", allFreeSpc);

        return ("listFreeSpaces");

    }

    @RequestMapping(value = "/ListFSpc", method = RequestMethod.GET)
    public String listFixed( Model model) {

        List<Space> allFSpc = spaceFixedRepository.findByFixed(true);

        model.addAttribute("allFSpc", allFSpc);

        return ("listFixedSpaces");

    }



    @RequestMapping(value="/saveUser", method= RequestMethod.POST)
    public String saveUser(@Valid AddUserModel addUserModel, BindingResult bindingResult) {
        new AddUserModelValidator().validate(addUserModel, bindingResult);
        if (bindingResult.hasErrors()) {
            return "newUser";
        }
        return "userAdded";
    }


    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String getById(Model model)
    {
         model.addAttribute("account", accountIdRepository.findById(2));

         return "editProfile";
    }

    @RequestMapping(value = {"/myBooking"})
    public ModelAndView booking(Model model) {

        model.addAttribute("username", "Michal");
        model.addAttribute("id", 2);

        try {

            if (bookingRepository.findByUser(2) != null) {

                Booking booking = bookingRepository.findByUser(2);

                String message = "You have booked a space for tomorrow with following ID";

                model.addAttribute("message", message);

                int spaceId = booking.getSpace();

                int bookingId = booking.getId();

                model.addAttribute("space", spaceId);

                model.addAttribute("bookingId", bookingId);

            } else {

                if(bookingsForTodayRepository.findByUser(2) != null) {

                    BookingForToday bookingForToday = bookingsForTodayRepository.findByUser(2);

                    String message = "You have booked a space for today with following ID";

                    model.addAttribute("message", message);

                    int spaceId = bookingForToday.getSpace();

                    int bookingId = bookingForToday.getId();

                    model.addAttribute("space", spaceId);

                    model.addAttribute("bookingId", bookingId);

                } else {

                    model.addAttribute("bookingId", 0);

                    model.addAttribute("space", 0);


                    model.addAttribute("message", "You don't have a booking.");
                }

            }


        } catch (Exception e) {

           System.out.println(e);
        }


        return new ModelAndView("bookingsPage", "booking", new Booking());
    }


    @RequestMapping(value = "/ListBookings", method = RequestMethod.GET)
    public String listBookngs(Model model) {

        List<Booking> allBookings = bookingRepository.findAll();

        model.addAttribute("allBookings", allBookings);

        return ("listBookings");

    }

    @RequestMapping(value = "/createBooking", method = RequestMethod.GET)
    public ModelAndView createBooking(Model model)
    {
        model.addAttribute("username", "Michal");
        model.addAttribute("id", 2);

        List<Space> allFreeSpc = spaceBookedRepository.findByBooked(false);

        model.addAttribute("allFreeSpc", allFreeSpc);


        return new ModelAndView("createBooking",
                "booking", new Booking());
    }

}
