package com.yajima.restaurants.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record RestaurantDTO(
        UUID id,
        String name,
        String foodType,
        String address,
        LocalDateTime startingHour,
        LocalDateTime finishingHour
) {
}
