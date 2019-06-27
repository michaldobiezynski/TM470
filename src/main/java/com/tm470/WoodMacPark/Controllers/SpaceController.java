package com.tm470.WoodMacPark.Controllers;

import com.tm470.WoodMacPark.Models.Account;
import com.tm470.WoodMacPark.Models.Space;
import com.tm470.WoodMacPark.Repositories.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spaces")
public class SpaceController {

    @Autowired
    private SpaceRepository spaceRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Space> list() {
        return spaceRepository.findAll();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Space create(@RequestBody Space space)
    {
        return spaceRepository.saveAndFlush(space);
    }

}
