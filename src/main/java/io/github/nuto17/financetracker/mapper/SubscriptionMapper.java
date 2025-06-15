package io.github.nuto17.financetracker.mapper;

import io.github.nuto17.financetracker.dto.SubscriptionDto;
import io.github.nuto17.financetracker.model.Subscription;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    Subscription toModel(SubscriptionDto subscriptionDto);
    SubscriptionDto toDto(Subscription subscription);

    List<Subscription> toModel(List<SubscriptionDto> subscriptionsDto);
    List<SubscriptionDto> toDto(List<Subscription> subscriptions);
}
