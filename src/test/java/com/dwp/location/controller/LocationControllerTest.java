package com.dwp.location.controller;

import com.dwp.location.app.LocationApp;
import com.dwp.location.client.Client;
import com.dwp.location.model.User;
import com.dwp.location.service.impl.LocationServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = LocationApp.class)
@WebMvcTest(controllers = LocationController.class)
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LocationServiceImpl locationService;

    @MockBean
    Client client;

    @MockBean
    RestTemplate restTemplate;

    @Test
    public void testClientsWithinRadius() throws Exception {
        when(locationService.getUserForLocation(any(), any())).thenReturn(getUsers());

        mockMvc.perform(get("/api/v1/location/LONDON/distance/150")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void testClientsWithinDefaultRadius() throws Exception {
        when(locationService.getUserForLocation(any(), any())).thenReturn(getUsers());

        mockMvc.perform(get("/api/v1/location/LONDON/distance")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void testClientsWithinMalformedUrl() throws Exception {
        when(locationService.getUserForLocation(any(), any())).thenReturn(getUsers());

        mockMvc.perform(get("/api/v1/location/LONDON/distance1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testClientWithInvalidCityName() throws Exception {
        when(locationService.getUserForLocation(any(), any())).thenReturn(getUsers());

        mockMvc.perform(get("/api/v1/location/BOURNEMOUTH/distance")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private List<User> getUsers() {
        User user1 = new User();
        user1.setEmail("abc@abc.com");
        user1.setFirstName("Firstname");
        user1.setLastName("Lastname");
        user1.setIpAddress("192.168.1.0");
        user1.setLatitude(Double.valueOf(51.000));
        user1.setLongitude(Double.valueOf(-1.009));

        User user2 = new User();
        user2.setEmail("abc@abc.com");
        user2.setFirstName("Firstname");
        user2.setLastName("Lastname");
        user2.setIpAddress("192.168.1.0");
        user2.setLatitude(Double.valueOf(51.000));
        user2.setLongitude(Double.valueOf(-1.009));

        return List.of(user1, user2);
    }
}
