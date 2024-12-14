package com.yajima.restaurants.bdd;

import com.yajima.restaurants.domain.entities.customer.Customer;
import com.yajima.restaurants.infra.persistence.CustomerEntity;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.awt.*;

import static io.restassured.RestAssured.given;


public class StepDefinition {

    private Response response;
    private Customer customerResponse;

    private final String ENDPOINT_API_CUSTOMERS = "http://localhost:8080/customers";

    @Quando("registrar um novo customer")
    public void registrar_um_novo_customer() {
        var customerRequest = generateCustomerEntity();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(customerRequest)
                .when()
                .post(ENDPOINT_API_CUSTOMERS);
    }

    @Então("o customer é registrado com sucesso")
    public void o_customer_é_registrado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());

    }




    private CustomerEntity generateCustomerEntity(){
        return CustomerEntity.builder()
                .name("John")
                .cpf("333.333.333-33")
                .email("john.batista@gmail.com")
                .build();
    }
}
