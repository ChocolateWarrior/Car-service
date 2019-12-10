package com.components.services;

import com.components.dto.CarDto;
import com.components.dto.UserDTO;
import com.components.entities.Car;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class GatewayService {

    private String bookingServiceBackPrefix;
    private String mainServiceBackPrefix;
    private String authServiceBackPrefix;
    private RestTemplate restTemplate;
    private HttpHeaders headers;


//    url.auth-service = http://localhost:8094/auth
//    url.booking-service = http://localhost:8093/booking
//    url.main-sevice = http://localhost:8090/cars
    @Autowired
    public GatewayService(@Value("${url.booking-service}") String bookingServiceBackPrefix,
                          @Value("${url.main-service}") String mainServiceBackPrefix,
                          @Value("${url.auth-service}") String authServiceBackPrefix,
                          RestTemplateBuilder restTemplateBuilder) {
        this.authServiceBackPrefix = authServiceBackPrefix;
        this.bookingServiceBackPrefix = bookingServiceBackPrefix;
        this.mainServiceBackPrefix = mainServiceBackPrefix;
        this.restTemplate= restTemplateBuilder.build();
    }

    public boolean register(UserDTO userDTO){
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("username", userDTO.getUsername());
        userJsonObject.put("password", userDTO.getPassword());
        HttpEntity<String> request =
                new HttpEntity<String>(userJsonObject.toString(), headers);

        ResponseEntity<String> responseEntityStr = restTemplate.
                postForEntity(authServiceBackPrefix + "/register",
                        request,
                        String.class);

        return Boolean.getBoolean(responseEntityStr.toString());
    }

    public boolean book(long id) {

        Car car = getById(id);
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject carJsonObject = new JSONObject();
        carJsonObject.put("id", car.getId());
        carJsonObject.put("brand", car.getBrand());
        carJsonObject.put("model", car.getModel());
        carJsonObject.put("color", car.getColor());
        carJsonObject.put("engine", car.getEngine());
        carJsonObject.put("engineCapacity", car.getEngineCapacity());
        carJsonObject.put("price", car.getPrice());
        carJsonObject.put("transmission", car.getTransmission());
        carJsonObject.put("driveLayout", car.getDriveLayout());

        HttpEntity<String> request =
                new HttpEntity<String>(carJsonObject.toString(), headers);

        ResponseEntity<String> responseEntityStr = restTemplate.
                postForEntity(bookingServiceBackPrefix + "/book", request, String.class);

        return Boolean.getBoolean(responseEntityStr.toString());
    }


    public List<Car> getAll() {
        try {
            ResponseEntity<List<Car>> answer = restTemplate
                    .exchange(
                            mainServiceBackPrefix + "/all",
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<Car>>() {});
            return answer.getBody();
        } catch (Exception e) {

        }
        return new ArrayList<>();

    }

    public Car getById(long id) {
        try {
            ResponseEntity<Car> answer = restTemplate
                    .getForEntity(mainServiceBackPrefix +
                            "/" + id, Car.class);
            return answer.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Car();
    }
}
