package com.dwp.location.controller;

import com.dwp.location.model.User;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationControllerTestIT {

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testNormalLoad() {
        ResponseEntity<User[]> users = restTemplate.getForEntity( "https://dwp-techtest.herokuapp.com/users", User[].class);
        assertThat(users.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    }
}
