package com.dwp.location.enums;

import com.dwp.location.model.Coordinates;

public enum Location {
    LONDON(51.50853 , -0.12574),
    MANCHESTER(53.4808, 2.2426),
    NEWCASTLE(54.9783, 1.6178),
    SHEFFIELD(53.3811, 1.4701);

    public Double latitude;
    public Double longitude;

    Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinates getCoordinates() {
        return new Coordinates(this.latitude, this.longitude);
    }
}
