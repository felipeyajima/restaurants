package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.domain.entities.booking.Booking;
import com.yajima.restaurants.domain.entities.customer.Customer;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.domain.entities.table.Table;
import com.yajima.restaurants.infra.persistence.BookingEntity;
import com.yajima.restaurants.infra.persistence.CustomerEntity;
import com.yajima.restaurants.infra.persistence.RestaurantEntity;
import com.yajima.restaurants.infra.persistence.TableEntity;

public class BookingEntityMapper {

    public BookingEntity toEntity(Booking booking){
        return new BookingEntity(
                booking.getId(),
                booking.getStatus(),
                booking.getBookingStart(),
                booking.getBookingFinish(),
                new TableEntity(
                        booking.getTable().getId(),
                        booking.getTable().getTableNumber(),
                        booking.getTable().getNumberOfChairs(),
                        booking.getTable().getStatus(),
                            new RestaurantEntity(
                                    booking.getTable().getRestaurant().getId(),
                                    booking.getTable().getRestaurant().getName(),
                                    booking.getTable().getRestaurant().getCnpj(),
                                    booking.getTable().getRestaurant().getFoodType(),
                                    booking.getTable().getRestaurant().getStartingHour(),
                                    booking.getTable().getRestaurant().getFinishingHour(),
                                    booking.getTable().getRestaurant().getPostalCode(),
                                    booking.getTable().getRestaurant().getAddressNumber(),
                                    booking.getTable().getRestaurant().getOpenOnlyOnBusinessDay()
                            )
                ),
                new CustomerEntity(
                        booking.getCustomer().getId(),
                        booking.getCustomer().getName(),
                        booking.getCustomer().getCpf(),
                        booking.getCustomer().getEmail()
                )
        );
    }

    public Booking toDomain(BookingEntity entity){
        return new Booking(
                entity.getId(),
                entity.getStatus(),
                entity.getBookingStart(),
                entity.getBookingFinish(),
                new Table(
                        entity.getTableEntity().getId(),
                        entity.getTableEntity().getTableNumber(),
                        entity.getTableEntity().getNumberOfChairs(),
                        entity.getTableEntity().getStatus(),
                            new Restaurant(
                                    entity.getTableEntity().getRestaurantEntity().getId(),
                                    entity.getTableEntity().getRestaurantEntity().getName(),
                                    entity.getTableEntity().getRestaurantEntity().getCnpj(),
                                    entity.getTableEntity().getRestaurantEntity().getFoodType(),
                                    entity.getTableEntity().getRestaurantEntity().getStartingHour(),
                                    entity.getTableEntity().getRestaurantEntity().getFinishingHour(),
                                    entity.getTableEntity().getRestaurantEntity().getPostalCode(),
                                    entity.getTableEntity().getRestaurantEntity().getAddressNumber(),
                                    entity.getTableEntity().getRestaurantEntity().getOpenOnlyOnBusinessDay()
                                    )
                ),
                new Customer(
                        entity.getCustomerEntity().getId(),
                        entity.getCustomerEntity().getName(),
                        entity.getCustomerEntity().getCpf(),
                        entity.getCustomerEntity().getEmail()
                )

        );

    }
}
