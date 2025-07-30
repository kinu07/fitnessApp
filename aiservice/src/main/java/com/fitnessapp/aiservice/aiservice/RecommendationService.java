package com.fitnessapp.aiservice.aiservice;

import com.fitnessapp.aiservice.airepo.RecommendationRepo;
import com.fitnessapp.aiservice.model.Recommendation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepo recommendationRepo;

    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepo.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        return recommendationRepo.findByActivityId(activityId)
                .orElseThrow(()->
                        new RuntimeException("No recommendation found for this activity "+ activityId));
    }
}
