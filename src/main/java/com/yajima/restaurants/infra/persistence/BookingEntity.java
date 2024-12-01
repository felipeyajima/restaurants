package com.yajima.restaurants.infra.persistence;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name="booking_db")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String status;
    private LocalDateTime bookingStart;
    private LocalDateTime bookingFinish;
    @ManyToOne
    @JoinColumn(name="table_id")
    private TableEntity tableEntity;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity customerEntity;

    public BookingEntity() {
    }

    public BookingEntity(UUID id, String status, LocalDateTime bookingStart, LocalDateTime bookingFinish, TableEntity tableEntity, CustomerEntity customerEntity) {
        this.id = id;
        this.status = status;
        this.bookingStart = bookingStart;
        this.bookingFinish = bookingFinish;
        this.tableEntity = tableEntity;
        this.customerEntity = customerEntity;
    }

}
