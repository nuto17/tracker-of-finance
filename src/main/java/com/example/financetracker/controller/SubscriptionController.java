package com.example.financetracker.controller;

import com.example.financetracker.dto.SubscriptionDto;
import com.example.financetracker.mapper.SubscriptionMapper;
import com.example.financetracker.model.Subscription;
import com.example.financetracker.service.SubscriptionService;
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
        return mapper.toDto(subscriptions);
    }

    @GetMapping("/{id}")
    public SubscriptionDto getSubscriptionById(@PathVariable("id") Long id){
        Subscription subscriptionById = subscriptionService.getSubscriptionById(id);
        return mapper.toDto(subscriptionById);
    }

    @PostMapping
    public SubscriptionDto createSubscription(@RequestBody SubscriptionDto subscriptionDto){
        Subscription subscription = mapper.toModel(subscriptionDto);
        Subscription createdSubscription = subscriptionService.createSubscription(subscription);
        return mapper.toDto(createdSubscription);
    }

    @PutMapping("/{id}")
    public SubscriptionDto updateSubscription(@PathVariable("id") Long id, @RequestBody SubscriptionDto subscriptionDto){
        Subscription subscription = mapper.toModel(subscriptionDto);
        Subscription updatedSubscription = subscriptionService.updateSubscription(subscription, id);
        return mapper.toDto(updatedSubscription);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscriptionById(@PathVariable("id") Long id){
        subscriptionService.deleteSubscriptionById(id);
    }
}
