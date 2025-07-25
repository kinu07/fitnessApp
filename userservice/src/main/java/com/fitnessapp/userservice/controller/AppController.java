package com.fitnessapp.userservice.controller;

import com.fitnessapp.userservice.dto.RegisterRequest;
import com.fitnessapp.userservice.dto.UserResponse;
import com.fitnessapp.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor // no need of autowiring
public class AppController {

    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable String userId){
        return  ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        return  ResponseEntity.ok(userService.register(request));
    }

    @DeleteMapping("/{userid}")
    public String deleteUserByEmail(@PathVariable String userId){
        return (userService.deleteUser(userId));
    }


}
