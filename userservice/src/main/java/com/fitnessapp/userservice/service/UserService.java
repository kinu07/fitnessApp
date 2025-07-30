package com.fitnessapp.userservice.service;

import com.fitnessapp.userservice.dto.RegisterRequest;
import com.fitnessapp.userservice.dto.UserResponse;
import com.fitnessapp.userservice.model.User;
import com.fitnessapp.userservice.userrepository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepo userRepo;
    public UserResponse register(RegisterRequest request) {

        if(userRepo.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email is already Present");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        User savedUser = userRepo.save(user);

        UserResponse ur = new UserResponse();
        ur.setId(savedUser.getId());
        ur.setEmail(savedUser.getEmail());
        ur.setPassword(savedUser.getPassword());
        ur.setFirstName(savedUser.getFirstName());
        ur.setLastName(savedUser.getLastName());
        ur.setCreatedAt(savedUser.getCreatedAt());
        ur.setUpdatedAt(savedUser.getUpdatedAt());

        return ur;
    }

    public UserResponse getUserProfile(String userId) {

        User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User Id soes not exists"));
        UserResponse ur = new UserResponse();
        ur.setId(user.getId());
        ur.setEmail(user.getEmail());
        ur.setFirstName(user.getFirstName());
        ur.setLastName(user.getLastName());
        ur.setCreatedAt(user.getCreatedAt());
        ur.setUpdatedAt(user.getUpdatedAt());
        return ur;

    }


    public String deleteUser(String userId) {

        userRepo.deleteById(userId);
        return "User Is Deleted";
    }

    public Boolean existsByUserId(String userId) {
        log.info("calling user validation api in activity service for userid {}", userId);
        return userRepo.existsById(userId);
    }
}
