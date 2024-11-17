package com.yajima.restaurants.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TableRepository extends JpaRepository<TableEntity, UUID> {

    @Query("select u from TableEntity u where u.restaurantEntity.id = ?1")
    List<TableEntity> findByRestaurantId(UUID id);

}
