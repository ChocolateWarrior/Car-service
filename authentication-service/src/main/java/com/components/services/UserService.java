package com.components.services;

import com.components.dto.UserDTO;
import com.components.entities.User;
import com.components.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public boolean isUserRegistered(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


    public boolean saveNewUser(UserDTO userdto) {
        if (isUserRegistered(userdto.getUsername())) {
            return false;
        }
        User user = User
                .builder()
                .username(userdto.getUsername())
                .password(userdto.getPassword())
                .build();
        userRepository.save(user);
        return true;
    }

}
