package com.components.services;

import com.components.dto.UserDTO;
import com.components.entities.User;
import com.components.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            log.warn("User not found!");
            throw new UsernameNotFoundException(username);
        }

        return user;
    }


    public boolean saveNewUser(UserDTO userdto) {
        User userFromDb = userRepository.findByUsername(userdto.getUsername());

        if (userFromDb != null) {
            log.warn("login not unique!");
            return false;
        }
        User user = User
                .builder()
                .username(userdto.getUsername())
                .password(passwordEncoder.encode(userdto.getPassword()))
                .build();
        userRepository.save(user);
        log.info("User was saved. Username : " + user.getUsername());
        return true;
    }

}
