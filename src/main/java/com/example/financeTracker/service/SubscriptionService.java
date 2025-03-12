package com.example.financeTracker.service;

import com.example.financeTracker.model.Subscription;
import com.example.financeTracker.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public List<Subscription> getSubscriptions(){
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions;
    }

    public Subscription getSubscriptionById(Long id){
        Subscription subscriptionById = subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("subscription with required id doesn't exist"));
        return subscriptionById;
    }

    public Subscription createSubscription(Subscription subscription){
        Subscription createdSubscription = subscriptionRepository.save(subscription);
        return createdSubscription;
    }

    public Subscription updateSubscription(Subscription subscription, Long id){
        Subscription subscriptionById = getSubscriptionById(id);
        Subscription buildedSubscription = Subscription
                .builder()
                .id(subscriptionById.getId())
                .name(subscription.getName())
                .price(subscription.getPrice())
                .dateToPay(subscription.getDateToPay())
                .build();
        Subscription savedBuildedSubscription = subscriptionRepository.save(buildedSubscription);
        return savedBuildedSubscription;
    }

    public void deleteSubscriptionById(Long id){
        Subscription subscriptionById = getSubscriptionById(id);
        subscriptionRepository.delete(subscriptionById);
    }
}
