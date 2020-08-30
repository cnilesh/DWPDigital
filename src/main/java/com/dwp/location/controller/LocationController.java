package com.dwp.location.controller;

import com.dwp.location.enums.Location;
import com.dwp.location.exception.DWPInvalidDataException;
import com.dwp.location.model.Coordinates;
import com.dwp.location.model.User;
import com.dwp.location.service.LocationService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = {"/location/{location}/distance/{distance}", "/location/{location}/distance"}, method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsersForLocation(@PathVariable Location location, @PathVariable(name = "distance", required = false) Optional<Integer> optDistance) {
        Integer distance = optDistance.orElse(50);
        return ResponseEntity.ok(locationService.getUserForLocation(location, distance));
    }

    @GetMapping("/distance")
    public ResponseEntity<Double> distanceBetweenCities(@RequestParam("sourceCity") Location source, @RequestParam("destinationCity") Location destination) {
        if(source == null || destination == null)
            throw new DWPInvalidDataException("Invalid Data found, please try with valid data");
        return ResponseEntity.ok(locationService.getDistanceBetweenCoordinates(source.getCoordinates(), destination.getCoordinates()));
    }

    @GetMapping("/distance/coordinates")
    public Double distanceBetweenCoordinates(@RequestParam Map<String, String> coordiatesMap) {
        if(!isValid(coordiatesMap))
            throw new DWPInvalidDataException("Invalid Data found, please try with valid data");
        Coordinates sourceCoordinates = new Coordinates(Double.valueOf(coordiatesMap.get("lat1")), Double.valueOf(coordiatesMap.get("lon1")));
        Coordinates destinationCoordinates = new Coordinates(Double.valueOf(coordiatesMap.get("lat2")), Double.valueOf(coordiatesMap.get("lon2")));
        return locationService.getDistanceBetweenCoordinates(sourceCoordinates, destinationCoordinates);
    }

    private boolean isValid(Map<String, String> coordinatesMap) {
        if(ObjectUtils.anyNull(coordinatesMap, coordinatesMap.get("lat1"), coordinatesMap.get("lon1"),
                coordinatesMap.get("lat2"), coordinatesMap.get("lon2")))
            return Boolean.FALSE;
        if(Double.valueOf(coordinatesMap.get("lat1")) >= 90 && Double.valueOf(coordinatesMap.get("lat1")) <= -90
            && Double.valueOf(coordinatesMap.get("lat2")) >= 90 && Double.valueOf(coordinatesMap.get("lat2")) <= -90)
            return Boolean.FALSE;
        if(Double.valueOf(coordinatesMap.get("lat1")) >= 180 && Double.valueOf(coordinatesMap.get("lat1")) <= -180
                && Double.valueOf(coordinatesMap.get("lat2")) >= 180 && Double.valueOf(coordinatesMap.get("lat2")) <= -180)
            return Boolean.FALSE;
        return Boolean.TRUE;
    }
}
