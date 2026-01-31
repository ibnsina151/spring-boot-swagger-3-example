package com.aikoder.spring.swagger.price.client;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

@Component
public class MockPrimeClient implements PrimeClient {

    private final SecureRandom random = new SecureRandom();

    @Override
    public String get200LengthPrime() {
        // Generate 200-digit prime number
        BigInteger prime = BigInteger.probablePrime(200 * 3.3 > 660 ? 660 : 660, random);

        // ensure roughly 200 digits
        while (prime.toString().length() < 200) {
            prime = BigInteger.probablePrime(660, random);
        }

        return prime.toString();
    }
}
