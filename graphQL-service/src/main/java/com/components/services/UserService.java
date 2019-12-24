package com.components.services;

import com.components.entities.Car;
import com.components.entities.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final String USER_SERVICE_URL = "localhost:8096/users";
    private RestTemplate restTemplate;

    public UserService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<User> findMultiple(long count) {

        try {
            ResponseEntity<List<User>> answer = restTemplate
                    .exchange(
                            USER_SERVICE_URL +
                                    "?count=" + count,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<User>>() {
                            });
            return answer.getBody();
        } catch (Exception e) {

        }
        return new ArrayList<>();

    }

    public User findById(long id) {

        try {
            ResponseEntity<User> answer = restTemplate
                    .getForEntity(USER_SERVICE_URL +
                            "/" + id, User.class);
            return answer.getBody();
        } catch (Exception e) {

        }
        return new User();
    }
}
