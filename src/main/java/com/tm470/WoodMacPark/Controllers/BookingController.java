package com.tm470.WoodMacPark.Controllers;


import com.tm470.WoodMacPark.Models.Booking;
import com.tm470.WoodMacPark.Models.Space;
import com.tm470.WoodMacPark.Repositories.BookingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

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
        bookingRepository.saveAndFlush(booking);

        redirectAttributes.addFlashAttribute("message", "Booking successfully created.");

        return new ModelAndView("redirect:/newB", "booking",
                new Booking());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Booking update(@PathVariable int id, @RequestBody Booking booking)
    {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(booking, existingBooking);
        return bookingRepository.saveAndFlush(existingBooking);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Booking delete(@PathVariable int id)
    {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        bookingRepository.delete(existingBooking);
        return existingBooking;
    }


}
