package com.dwp.location.service;

import com.dwp.location.client.Client;
import com.dwp.location.enums.Location;
import com.dwp.location.exception.DWPInvalidDataException;
import com.dwp.location.model.User;
import com.dwp.location.service.impl.LocationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {

    private LocationService service;

    @Mock
    private Client client;

    @Before
    public void setUp() {
        service = new LocationServiceImpl(client);
    }

    @Test
    public void testWithin50MilesLondon() {
        when(client.getAllUsers()).thenReturn(getUsers());
        List<User> users = service.getUserForLocation(Location.LONDON, 50);
        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(e -> e.getEmail().equals("abc@within50.com")));
    }

    @Test
    public void testWithin150MilesLondon() {
        when(client.getAllUsers()).thenReturn(getUsers());
        List<User> users = service.getUserForLocation(Location.LONDON, 150);
        assertEquals(3, users.size());
        assertTrue(users.stream().anyMatch(e -> e.getEmail().equals("abc@within50.com")));
        assertTrue(users.stream().anyMatch(e -> e.getEmail().equals("abc@within150.com")));
        assertTrue(users.stream().anyMatch(e -> e.getEmail().equals("abc@londoncoordinates.com")));
    }

    @Test
    public void testWithin0MilesLondon() {
        when(client.getAllUsers()).thenReturn(getUsers());
        List<User> users = service.getUserForLocation(Location.LONDON, 0);
        assertEquals(1, users.size());
        assertTrue(users.stream().anyMatch(e -> e.getEmail().equals("abc@londoncoordinates.com")));
    }

    @Test(expected = DWPInvalidDataException.class)
    public void testInvalidData() {
        when(client.getAllUsers()).thenReturn(getInvalidUsers());
        service.getUserForLocation(Location.LONDON, 0);
    }

    private List<User> getUsers() {
        User user1 = new User();
        user1.setEmail("abc@within50.com");
        user1.setFirstName("Firstname");
        user1.setLastName("Lastname");
        user1.setIpAddress("192.168.1.0");
        user1.setLatitude(Double.valueOf(51.6553959));
        user1.setLongitude(Double.valueOf(0.0572553));

        User user2 = new User();
        user2.setEmail("abc@within150.com");
        user2.setFirstName("Firstname");
        user2.setLastName("Lastname");
        user2.setIpAddress("192.168.1.0");
        user2.setLatitude(Double.valueOf(49.6355347));
        user2.setLongitude(Double.valueOf(-1.6272462));

        User user3 = new User();
        user3.setEmail("abc@londoncoordinates.com");
        user3.setFirstName("Firstname");
        user3.setLastName("Lastname");
        user3.setIpAddress("192.168.1.0");
        user3.setLatitude(Double.valueOf(51.50853));
        user3.setLongitude(Double.valueOf(-0.12574));

        return List.of(user1, user2, user3);
    }

    private List<User> getInvalidUsers() {
        User user1 = new User();
        user1.setEmail("abc@within50.com");
        user1.setFirstName("Firstname");
        user1.setLastName("Lastname");
        user1.setIpAddress("192.168.1.0");
        user1.setLatitude(null);
        user1.setLongitude(Double.valueOf(0.0572553));


        return List.of(user1);
    }
}
