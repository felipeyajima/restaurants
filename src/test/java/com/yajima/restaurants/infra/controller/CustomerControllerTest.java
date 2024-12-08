package com.yajima.restaurants.infra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yajima.restaurants.application.usecases.customer.CreateCustomer;
import com.yajima.restaurants.application.usecases.customer.DeleteCustomer;
import com.yajima.restaurants.application.usecases.customer.FindCustomer;
import com.yajima.restaurants.application.usecases.customer.ListCustomers;
import com.yajima.restaurants.domain.entities.customer.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.UUID;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateCustomer createCustomer;
    @Mock
    private ListCustomers listCustomers;
    @Mock
    private FindCustomer findCustomer;
    @Mock
    private DeleteCustomer deleteCustomer;


    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        CustomerController customerController = new CustomerController(createCustomer, listCustomers, findCustomer, deleteCustomer);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }


    @Test
    void shouldAllowCreateCustomer() throws Exception {

        //arrange
        var customer = generateCustomer();
        when(createCustomer.createCustomer(any(Customer.class))).thenAnswer(c -> c.getArgument(0));

        mockMvc.perform(post("/customers")
                        .contentType("application/json")
                        .content(asJsonString(customer)))
                .andExpect(status().isOk());

        verify(createCustomer, times(1)).createCustomer(any(Customer.class));
    }



    @Test
    void shouldAllowFindCustomer(){
        fail("not implemented");
    }

    @Test
    void shouldAllowListCustomers(){
        fail("not implemented");
    }


    public static String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }


    private Customer generateCustomer(){
        Customer customer1 = new Customer(UUID.randomUUID(), "John", "333.333.333-00", "teste@teste.com.br");
        return customer1;
    }
    /*
    private CustomerEntity generateCustomerEntity(){
        return CustomerEntity.builder()
                .name("John")
                .cpf("333.333.333-33")
                .email("john.batista@gmail.com")
                .build();
    }

    private CustomerDto generateCustomerDto(){
        CustomerDto customerDto = new CustomerDto(UUID.randomUUID(), "John", "333.333.333-00", "teste@teste.com.br");
        return customerDto;
    }

     */

}
