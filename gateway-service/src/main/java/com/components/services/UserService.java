package com.components.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Log4j2
@Service
public class UserService implements UserDetailsService {

    private String authServiceBackPrefix;
    private RestTemplate restTemplate;

    //url.auth-service=http://localhost:8094/auth
    @Autowired
    public UserService(@Value("${url.auth-service}") String authServiceBackPrefix,
                       RestTemplateBuilder restTemplateBuilder) {
        this.authServiceBackPrefix = authServiceBackPrefix;
        this.restTemplate = restTemplateBuilder.build();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ResponseEntity<UserDetails> answer = restTemplate
                    .getForEntity(authServiceBackPrefix +
                            "/username?username=" + username, UserDetails.class);
            return answer.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}