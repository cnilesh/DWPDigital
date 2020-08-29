package com.dwp.location.service;

import com.dwp.location.enums.Location;
import com.dwp.location.model.User;

import java.util.List;

public interface LocationService {

    List<User> getUserForLocation(Location location, Integer distance);
}
