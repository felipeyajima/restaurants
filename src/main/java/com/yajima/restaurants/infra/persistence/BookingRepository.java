package com.yajima.restaurants.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    //@Modifying
    //@Query("UPDATE BookingEntity u SET u.status='ativated' WHERE u.id = ?1")
    //void welcomeCustomer(UUID id);


}
