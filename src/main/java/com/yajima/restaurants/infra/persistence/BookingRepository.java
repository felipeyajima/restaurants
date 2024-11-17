package com.yajima.restaurants.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
}
