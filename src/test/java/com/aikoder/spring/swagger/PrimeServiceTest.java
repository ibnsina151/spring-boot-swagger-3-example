package com.aikoder.spring.swagger;

import com.example.prime.client.PrimeClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PrimeServiceTest {

    @Test
    void shouldReturnPrimeFromClient() {

        PrimeClient mockClient = Mockito.mock(PrimeClient.class);

        String mockPrime = "99999999999999999999".repeat(10); // 200 chars

        Mockito.when(mockClient.get200LengthPrime())
                .thenReturn(mockPrime);

        PrimeService service = new PrimeService(mockClient);

        String result = service.getPrime200();

        assertNotNull(result);
        assertEquals(200, result.length());
        assertEquals(mockPrime, result);
    }
}
