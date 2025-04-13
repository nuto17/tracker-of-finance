package com.example.financetracker.mapper;

import com.example.financetracker.dto.SubscriptionDto;
import com.example.financetracker.model.Subscription;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    Subscription toModel(SubscriptionDto subscriptionDto);
    SubscriptionDto toDto(Subscription subscription);

    List<Subscription> toModel(List<SubscriptionDto> subscriptionsDto);
    List<SubscriptionDto> toDto(List<Subscription> subscriptions);
}
