package com.dwp.location.client;

import com.dwp.location.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {

    @Value("${source-url}")
    private String sourceUrl;

    @Mock
    RestTemplate restTemplate;

    private Client client;

    @Before
    public void setUp() {
        client = new Client(restTemplate);
    }

    @Test
    public void testWhenProperResponse() {
        when(restTemplate.getForEntity(sourceUrl+"/users", User[].class)).thenReturn(new ResponseEntity(getUsers(), HttpStatus.OK));
        List<User> users = client.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void testWhenFailedStatus() {
        when(restTemplate.getForEntity(sourceUrl+"/users", User[].class)).thenReturn(new ResponseEntity(getUsers(), HttpStatus.BAD_GATEWAY));
        List<User> users = client.getAllUsers();
        assertEquals(0, users.size());
    }

    @Test
    public void testWhenNullResponseBody() {
        when(restTemplate.getForEntity(sourceUrl+"/users", User[].class)).thenReturn(new ResponseEntity(null, HttpStatus.BAD_GATEWAY));
        List<User> users = client.getAllUsers();
        assertEquals(0, users.size());
    }

    private User[] getUsers() {
        User[] users = new User[2];
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

        users[0] = user1;
        users[1] = user2;
        return users;
    }
}
