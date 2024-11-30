package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.infra.persistence.CustomerEntity;
import com.yajima.restaurants.infra.persistence.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


//@SpringBootTest
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class CustomerJpaRepositoryIT {

    // TESTES DE INTEGRAÇÃO

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldAllowCreateTable(){
        var totalOfRecords = customerRepository.count();
        assertThat(totalOfRecords).isNotNegative();
    }

    @Test
    void shouldAllowCreateCustomer(){
        // Arrange
        var customerEntity = generateCustomerEntity();

        //act
        var receivedCustomer = customerRepository.save(customerEntity);

        //assert
        assertThat(receivedCustomer)
                .isInstanceOf(CustomerEntity.class)
                .isNotNull();

        assertThat(receivedCustomer.getName()).isEqualTo(customerEntity.getName());
    }

    @Test
    void shouldAllowFindCustomer(){
        // arrange
        var customerEntity = generateCustomerEntity();
        CustomerEntity savedCustomerEntity = saveCustomer(customerEntity);

        // act
        var receivedCustomerEntityOptional = customerRepository.findById(savedCustomerEntity.getId());

        // assert
        assertThat(receivedCustomerEntityOptional)
                .isPresent();

        receivedCustomerEntityOptional.ifPresent(receivedCustomerEntity -> {
            assertThat(receivedCustomerEntity.getName()).isEqualTo(savedCustomerEntity.getName());
        });


    }

    @Test
    void shouldAllowListCustomers(){
        var customerEntity1 = generateCustomerEntity();
        var receivedCustomer1 = customerRepository.save(customerEntity1);

        var customerEntity2 = generateCustomerEntity();
        var receivedCustomer2 = customerRepository.save(customerEntity2);

        var customersList = customerRepository.findAll();
        assertThat(customersList).hasSizeGreaterThan(0);

    }


    // Generate an Object
    private CustomerEntity generateCustomerEntity(){
        return CustomerEntity.builder()
                .name("John")
                .cpf("333.333.333-33")
                .email("john.batista@gmail.com")
                .build();
    }

    // Save on Database the object
    private CustomerEntity saveCustomer(CustomerEntity customerEntity){
        return customerRepository.save(customerEntity);
    }

}
