package com.tm470.WoodMacPark.Controllers;

import com.tm470.WoodMacPark.Models.Account;
import com.tm470.WoodMacPark.Models.Space;
import com.tm470.WoodMacPark.Repositories.SpaceBookedRepository;
import com.tm470.WoodMacPark.Repositories.SpaceFixedRepository;
import com.tm470.WoodMacPark.Repositories.SpaceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/spaces")
public class SpaceController {

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private SpaceBookedRepository spaceBookedRepository;

    @Autowired
    private SpaceFixedRepository spaceFixedRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Space> list() {
        return spaceRepository.findAll();
    }

    @RequestMapping(value = "/all/booked/{booked}", method = RequestMethod.GET)
    public List<Space> listBooked(@PathVariable boolean booked) {
        return spaceBookedRepository.findByBooked(booked);
    }

    @RequestMapping(value = "/all/fixed/{fixed}", method = RequestMethod.GET)
    public List<Space> listFixed(@PathVariable boolean fixed) {
        return spaceFixedRepository.findByFixed(fixed);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute Space space,
                               RedirectAttributes redirectAttributes)
    {
       spaceRepository.saveAndFlush(space);

       redirectAttributes.addFlashAttribute("message", "Space successfully created.");

       return new ModelAndView("redirect:/newS", "space",
               new Space());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Space getById(@PathVariable int id)
    {
        return spaceRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Space update(@PathVariable int id, @RequestBody Space space)
    {
        Space existingSpace = spaceRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(space, existingSpace);
        return spaceRepository.saveAndFlush(existingSpace);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Space delete(@PathVariable int id)
    {
        Space existingSpace = spaceRepository.findById(id).orElse(null);
        spaceRepository.delete(existingSpace);
        return existingSpace;
    }

    @RequestMapping(value = "/newFixed", method = RequestMethod.POST)
    public ModelAndView createFixedSpace(@ModelAttribute Space space,
                                         RedirectAttributes redirectAttributes) {

        if(space.getId() != 0) {

            space.setBooked(true);

            space.setFixed(true);

            spaceRepository.saveAndFlush(space);

            List<Space> allFreeSpc = spaceBookedRepository.findByBooked(false);

            redirectAttributes.addFlashAttribute("allAcc", allFreeSpc);

            redirectAttributes.addFlashAttribute("message", "Fixed space successfully created.");

            return new ModelAndView("redirect:/admin/newFixedSpace", "space", new Space());

        }
        else {

            List<Space> allFreeSpc = spaceBookedRepository.findByBooked(false);

            redirectAttributes.addFlashAttribute("allAcc", allFreeSpc);

            redirectAttributes.addFlashAttribute("message", "You have not selected a space.");

            return new ModelAndView("redirect:/admin/newFixedSpace", "space", new Space());
        }


    }

    @RequestMapping(value = "/deleteFixed", method = RequestMethod.POST)
    public ModelAndView deleteFixedSpace(@ModelAttribute Space space,
                                         RedirectAttributes redirectAttributes) {


        if(space.getId() != 0) {

            space.setBooked(false);

            space.setFixed(false);

            spaceRepository.saveAndFlush(space);

            List<Space> allFreeSpc = spaceBookedRepository.findByBooked(false);

            redirectAttributes.addFlashAttribute("allAcc", allFreeSpc);

            redirectAttributes.addFlashAttribute("message", "Fixed space successfully removed.");

            return new ModelAndView("redirect:/admin/deleteFixedSpace", "space", new Space());

        }
        else {

            List<Space> allFreeSpc = spaceBookedRepository.findByBooked(false);

            redirectAttributes.addFlashAttribute("allAcc", allFreeSpc);

            redirectAttributes.addFlashAttribute("message", "You have not selected a space.");

            return new ModelAndView("redirect:/admin/deleteFixedSpace", "space", new Space());
        }

    }


}
