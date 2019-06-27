package com.tm470.WoodMacPark.Controllers;

import com.tm470.WoodMacPark.Models.Account;
import com.tm470.WoodMacPark.Models.Space;
import com.tm470.WoodMacPark.Repositories.SpaceBookedRepository;
import com.tm470.WoodMacPark.Repositories.SpaceFixedRepository;
import com.tm470.WoodMacPark.Repositories.SpaceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Space create(@RequestBody Space space)
    {
        return spaceRepository.saveAndFlush(space);
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

}
