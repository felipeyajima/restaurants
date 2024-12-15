package com.yajima.restaurants.performance;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;


public class PerformanceSimulation extends Simulation {
    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json");

    ActionBuilder RegisterValidCustomer = http("Register Valid Customer")
            .post("/customers")
            .body(StringBody("{\"name\":\"Jane Doe\",\"cpf\":\"000.000.000-00\",\"email\":\"jane.doe@test.com\"}"))
            .check(status().is(200));



    ScenarioBuilder scenarioCustomers = scenario("Customer API Performance Test")
            .exec(
                    http("Register Valid Customer")
                            .post("/customers")
                            .body(StringBody("{\"name\":\"John Doe\",\"cpf\":\"333.333.333-00\",\"email\":\"john.doe@test.com\"}"))
                            .check(status().is(200)) // Espera o status HTTP 201
            )
            .pause(1) // Pausa de 1 segundo
            .exec(
                    http("Register Invalid Customer - Invalid CPF")
                            .post("/customers")
                            .body(StringBody("{\"name\":\"Jane Doe\",\"cpf\":\"00000000000\",\"email\":\"jane.doe@test.com\"}"))
                            .check(status().is(404)) // Espera o status HTTP 400
            )
            .pause(1)
            .exec(
                    http("Register Invalid Customer - Invalid Email")
                            .post("/customers")
                            .body(StringBody("{\"name\":\"Mark Doe\",\"cpf\":\"333.333.333-00\",\"email\":\"invalid-email\"}"))
                            .check(status().is(404)) // Espera o status HTTP 400
            );
    {
        setUp(
                scenarioCustomers.injectOpen(
                        rampUsersPerSec(1).to(10).during(Duration.ofSeconds(10)), // Ramp-up de 1 a 10 usuários por segundo em 10 segundos
                        constantUsersPerSec(10).during(Duration.ofSeconds(20)), // 10 usuários por segundo por 20 segundos
                        rampUsersPerSec(10).to(1).during(Duration.ofSeconds(10)) // Ramp-down de 10 a 1 usuário por segundo em 10 segundos
                ) // Fechamento do parêntese do injectOpen
        ) // Fechamento do parêntese do setUp
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(1000) // A resposta deve ter um tempo máximo menor que 50ms
                );
    }

}