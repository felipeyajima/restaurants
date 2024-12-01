package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.infra.persistence.CustomerEntity;
import com.yajima.restaurants.infra.persistence.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class CustomerJpaRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;
    AutoCloseable openMocks;
    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception{
        openMocks.close();
    }


    @Test
    void shouldAllowCreateCustomer(){
        // ARRANGE - inicio
        var customerEntity = generateCustomerEntity();

        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        // ACT - nosso objetivo do ato
        var customerAllocated = customerRepository.save(customerEntity);

        // ASSERT - fazer as validações
        assertThat(customerAllocated).isNotNull().isEqualTo(customerEntity);

        //verifica se o repositorio foi chamado apenas uma vez quando executar o metodo save com o Customer
        verify(customerRepository, times(1)).save(customerEntity);
    }

    @Test
    void shouldAllowFindCustomer(){
        // Arrange
        var id = UUID.randomUUID();
        var customerEntity = generateCustomerEntity();
        customerEntity.setId(id);

        when(customerRepository.findById(any(UUID.class))).thenReturn(Optional.of(customerEntity));

        // act
        var returnedCustomerEntityOptional = customerRepository.findById(id);

        // assert
        assertThat(returnedCustomerEntityOptional).isPresent().containsSame(customerEntity);
        // garante que o customer recebido é o customer que foi gerado
        returnedCustomerEntityOptional.ifPresent(customerReceived -> {
            assertThat(customerReceived.getId()).isEqualTo(customerEntity.getId());
            assertThat(customerReceived.getName()).isEqualTo(customerEntity.getName());
        });

        verify(customerRepository, times(1)).findById(any(UUID.class));

    }

    @Test
    void shouldAllowListCustomers(){
        // Arrange
        var customer1 = generateCustomerEntity();
        var customer2 = generateCustomerEntity();
        var customerList = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customerList);

        // Act
        var customersReceived = customerRepository.findAll();

        // assert
        assertThat(customersReceived).hasSize(2).containsExactlyInAnyOrder(customer1, customer2);

        verify(customerRepository, times(1)).findAll();


    }

    @Test
    void shouldExcludeCustomer(){
        fail("teste nao implementado");
    }


    private CustomerEntity generateCustomerEntity(){
        return CustomerEntity.builder()
                .name("John")
                .cpf("333.333.333-33")
                .email("john.batista@gmail.com")
                .build();
    }

}
