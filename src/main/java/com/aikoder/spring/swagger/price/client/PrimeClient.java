package com.aikoder.spring.swagger.price.client;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "https://api.example.com")
public interface PrimeClient {

    @GetExchange("/prime/200")
    String get200LengthPrime();

}
