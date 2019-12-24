package com.components.services;

import com.components.entities.Car;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private RestTemplate restTemplate;
    private final String BOOKING_SERVICE_URL = "localhost:8093/booking";

    public BookingService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String bookByMutation(long id) {

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<String>(Long.valueOf(id).toString(), headers);
        URI uri = restTemplate.postForLocation(BOOKING_SERVICE_URL + "/book/" + id, request);

        return "Your preference with id: " + id + " was booked!";
    }

    public List<Car> findMultiple(long count) {

        try {
            ResponseEntity<List<Car>> answer = restTemplate
                    .exchange(
                            BOOKING_SERVICE_URL +
                                    "?count=" + count,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<Car>>() {
                            });
            return answer.getBody();
        } catch (Exception e) {

        }
        return new ArrayList<>();
    }

    public Car findById(long id) {

        try {
            ResponseEntity<Car> answer = restTemplate
                    .getForEntity(BOOKING_SERVICE_URL +
                            "/" + id, Car.class);
            return answer.getBody();
        } catch (Exception e) {

        }
        return new Car();
    }
}
