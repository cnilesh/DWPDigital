package com.dwp.location.client;

import com.dwp.location.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Client {

    private RestTemplate restTemplate;

    @Value("${source-url}")
    private String baseUrl;

    public Client(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {
        ResponseEntity<User[]> users = restTemplate.getForEntity(baseUrl + "/users", User[].class);
        if(isResponseValid(users))
            return Arrays.asList(users.getBody());
        return new ArrayList<>();
    }

    private boolean isResponseValid(ResponseEntity responseEntity) {
        if(responseEntity.getStatusCode() == HttpStatus.OK && responseEntity != null && responseEntity.getBody() != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
}
