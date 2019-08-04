package com.tm470.WoodMacPark.Controllers;


import com.tm470.WoodMacPark.Models.Booking;
import com.tm470.WoodMacPark.Models.Space;
import com.tm470.WoodMacPark.Repositories.BookingRepository;
import com.tm470.WoodMacPark.Repositories.SpaceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Booking> list()
    {
        return bookingRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Booking getById(@PathVariable int id)
    {
        return bookingRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute Booking booking,
                               RedirectAttributes redirectAttributes)
    {

        if(bookingRepository.findByUser(booking.getUser()) == null) {

            int spaceId = booking.getSpace();

            Space space = spaceRepository.findById(spaceId).orElse(null);

            space.setBooked(true);

            spaceRepository.saveAndFlush(space);

            bookingRepository.saveAndFlush(booking);

            redirectAttributes.addFlashAttribute("message", "Booking successfully created.");

            return new ModelAndView("redirect:/myBooking", "booking",
                    new Booking());
        } else {

            redirectAttributes.addFlashAttribute("message", "You already have a booking.");

            return new ModelAndView("redirect:/createBooking", "booking",
                    new Booking());
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Booking update(@PathVariable int id, @RequestBody Booking booking)
    {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(booking, existingBooking);
        return bookingRepository.saveAndFlush(existingBooking);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public ModelAndView delete(@PathVariable int id)
//    {
//        Booking existingBooking = bookingRepository.findById(id).orElse(null);
//        bookingRepository.delete(existingBooking);
//        return new ModelAndView("redirect:/myBooking", "null", null);
//    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ModelAndView delete(@PathVariable int id,
                               RedirectAttributes redirectAttributes)
    {
        try {
            if(bookingRepository.findById(id) != null) {

                Booking bookingToDelete = bookingRepository.findById(id).orElse(null);

                int spaceId = bookingToDelete.getSpace();

                Space space = spaceRepository.findById(spaceId).orElse(null);

                space.setBooked(false);

                spaceRepository.saveAndFlush(space);

                bookingRepository.delete(bookingToDelete);


                redirectAttributes.addFlashAttribute("message", "Booking was successfully deleted.");


            }

            else {
                redirectAttributes.addFlashAttribute("message", "You didn't have a booking.");

            }

            } catch (Exception e) {
            System.out.println(e);
        }
        return new ModelAndView("redirect:/createBooking", "null", null);


    }


}
