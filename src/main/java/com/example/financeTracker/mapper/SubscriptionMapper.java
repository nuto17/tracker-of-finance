package com.example.financeTracker.mapper;

import com.example.financeTracker.dto.SubscriptionDto;
import com.example.financeTracker.model.Subscription;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    Subscription toModel(SubscriptionDto subscriptionDto);
    SubscriptionDto toDto(Subscription subscription);

    List<Subscription> toModel(List<SubscriptionDto> subscriptionsDto);
    List<SubscriptionDto> toDto(List<Subscription> subscriptions);
}
