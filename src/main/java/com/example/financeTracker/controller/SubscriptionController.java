package com.example.financeTracker.controller;

import com.example.financeTracker.dto.SubscriptionDto;
import com.example.financeTracker.mapper.SubscriptionMapper;
import com.example.financeTracker.model.Subscription;
import com.example.financeTracker.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper mapper;

    @GetMapping
    public List<SubscriptionDto> getSubscriptions(){
        List<Subscription> subscriptions = subscriptionService.getSubscriptions();
        List<SubscriptionDto> subscriptionsDto = mapper.toDto(subscriptions);
        return subscriptionsDto;
    }

    @GetMapping("/{id}")
    public SubscriptionDto getSubscriptionById(@PathVariable("id") Long id){
        Subscription subscriptionById = subscriptionService.getSubscriptionById(id);
        SubscriptionDto subscriptionByIdDto = mapper.toDto(subscriptionById);
        return subscriptionByIdDto;
    }

    @PostMapping
    public SubscriptionDto createSubscription(@RequestBody SubscriptionDto subscriptionDto){
        Subscription subscription = mapper.toModel(subscriptionDto);
        Subscription createdSubscription = subscriptionService.createSubscription(subscription);
        SubscriptionDto createdSubscriptionDto = mapper.toDto(createdSubscription);
        return createdSubscriptionDto;
    }

    @PutMapping("/{id}")
    public SubscriptionDto updateSubscription(@PathVariable("id") Long id, @RequestBody SubscriptionDto subscriptionDto){
        Subscription subscription = mapper.toModel(subscriptionDto);
        Subscription updatedSubscription = subscriptionService.updateSubscription(subscription, id);
        SubscriptionDto updatedSubscriptionDto = mapper.toDto(updatedSubscription);
        return updatedSubscriptionDto;
    }

    @DeleteMapping("/{id}")
    public void deleteSubscriptionById(@PathVariable("id") Long id){
        subscriptionService.deleteSubscriptionById(id);
    }
}
