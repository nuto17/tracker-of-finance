package io.github.nuto17.financetracker.service;

import io.github.nuto17.financetracker.model.Subscription;
import io.github.nuto17.financetracker.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public List<Subscription> getSubscriptions(){
        return subscriptionRepository.findAll();
    }

    public Subscription getSubscriptionById(Long id){
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("subscription with required id doesn't exist"));
    }

    public Subscription createSubscription(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(Subscription subscription, Long id){
        Subscription subscriptionById = getSubscriptionById(id);
        Subscription buildedSubscription = Subscription.builder()
                .id(subscriptionById.getId())
                .name(subscription.getName())
                .price(subscription.getPrice())
                .dateToPay(subscription.getDateToPay())
                .build();
        return subscriptionRepository.save(buildedSubscription);
    }

    public void deleteSubscriptionById(Long id){
        subscriptionRepository.deleteById(id);
    }
}
