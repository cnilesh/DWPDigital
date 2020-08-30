package com.dwp.location.service.impl;

import com.dwp.location.client.Client;
import com.dwp.location.enums.Location;
import com.dwp.location.exception.DWPInvalidDataException;
import com.dwp.location.model.Coordinates;
import com.dwp.location.model.User;
import com.dwp.location.service.LocationService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private Client client;

    public LocationServiceImpl(Client client) {
        this.client = client;
    }

    @Override
    public Double getDistanceBetweenCoordinates(Coordinates source, Coordinates destination) {
        if(ObjectUtils.anyNull(source.getLatitude(), source.getLongitude(), destination.getLongitude(), destination.getLatitude())) {
            throw new DWPInvalidDataException("Either Source or Destination is invalid!");
        }
        if(source.equals(destination)) {
            return Double.valueOf(0);
        }
        double theta = source.getLongitude() - destination.getLongitude();
        double dist = Math.sin(deg2rad(source.getLatitude())) * Math.sin(deg2rad(destination.getLatitude())) + Math.cos(deg2rad(source.getLatitude())) * Math.cos(deg2rad(destination.getLatitude())) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    //Convert degrees to radians
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    //converts radians to degrees
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    @Override
    public List<User> getUserForLocation(Location location, Integer distance) {
        List<User> users = client.getAllUsers();

        // filter based on the city co-ordinates and the user co-ordinates
        List<User> usersWithinDistance = users.stream().filter(user -> getDistanceBetweenCoordinates(location.getCoordinates(), user.getCoordinates()) <= distance)
                .collect(Collectors.toList());
        return usersWithinDistance;
    }
}
