package com.dwp.location.service;

import com.dwp.location.enums.Location;
import com.dwp.location.model.Coordinates;
import com.dwp.location.model.User;

import java.util.List;

public interface LocationService {

    public Double getDistanceBetweenCoordinates(Coordinates source, Coordinates destination);

    List<User> getUserForLocation(Location location, Integer distance);
}
