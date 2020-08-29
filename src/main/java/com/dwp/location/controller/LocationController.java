package com.dwp.location.controller;

import com.dwp.location.enums.Location;
import com.dwp.location.model.User;
import com.dwp.location.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = {"/location/{location}/distance/{distance}", "/location/{location}/distance"}, method = RequestMethod.GET)
    public List<User> getUsersForLocation(@PathVariable Location location, @PathVariable(name = "distance", required = false) Optional<Integer> optDistance) {
        Integer distance = optDistance.orElse(50);
        return locationService.getUserForLocation(location, distance);
    }
}
