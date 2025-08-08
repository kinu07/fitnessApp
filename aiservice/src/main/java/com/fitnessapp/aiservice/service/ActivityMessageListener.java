package com.fitnessapp.aiservice.service;

import com.fitnessapp.aiservice.airepo.RecommendationRepo;
import com.fitnessapp.aiservice.model.Activity;
import com.fitnessapp.aiservice.model.Recommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAiService activityAiService;
    private final RecommendationRepo recommendationRepo;

    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity){
        log.info("Received activity for processing : {}", activity.getId());
      //  log.info("Generated Recommendation : {}", activityAiService.generateRecommendations(activity));
        Recommendation recommendation = activityAiService.generateRecommendations(activity);
        recommendationRepo.save(recommendation);
        log.info("Recommendation saved for activity: {}", activity.getId());
    }
}
