package com.fitnessapp.activityservice.controller;

import com.fitnessapp.activityservice.dto.ActivityRequest;
import com.fitnessapp.activityservice.dto.ActivityResponse;
import com.fitnessapp.activityservice.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {

    ActivityService activityService;

    @PostMapping("/register")
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request){
        return ResponseEntity.ok(activityService.trackActivity(request));
    }
    @GetMapping("user/{userId}")
    public ResponseEntity <List<ActivityResponse>> getActivities(@PathVariable String userId){
        return ResponseEntity.ok(activityService.getActivities(userId));
    }
    @GetMapping("/{activityId}")
    public ResponseEntity <ActivityResponse> getActivity(@PathVariable String activityId){
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }
}
