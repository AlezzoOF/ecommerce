package com.idos.apk.backend.tienda.tatuajes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BlacklistedTokenTest {
    BlacklistedToken blacklistedToken;
    BlacklistedToken blacklistedToken1;
    BlacklistedToken blacklistedToken2;

    @BeforeEach
    public void setup(){
        blacklistedToken =  BlacklistedToken.builder()
                .id("prueba")
                .token("prueba")
                .expirationDate(new Date(2000, Calendar.MARCH, 12))
                .build();

        blacklistedToken1 =  BlacklistedToken.builder()
                .id("prueba")
                .token("prueba")
                .expirationDate(new Date(2000, Calendar.MARCH, 12))
                .build();

        blacklistedToken2 =  BlacklistedToken.builder()
                .id("prueba2")
                .token("prueba2")
                .expirationDate(new Date(2222, Calendar.MARCH, 23))
                .build();

    }


    @Test
    void testConstructorAndGetters() {


        assertNotNull(blacklistedToken);
        assertEquals("prueba", blacklistedToken.getId());
        assertNotNull(blacklistedToken.getExpirationDate());
        assertEquals(new Date(2000, Calendar.MARCH, 12), blacklistedToken.getExpirationDate());
        assertEquals("prueba", blacklistedToken.getToken());
    }

    @Test
    void testSetters() {
        blacklistedToken.setId("2");
        blacklistedToken.setExpirationDate(new Date(2023, Calendar.AUGUST, 19));
        blacklistedToken.setToken("token");

        assertEquals("2", blacklistedToken.getId());
        assertEquals(new Date(2023, Calendar.AUGUST, 19), blacklistedToken.getExpirationDate());
        assertEquals("token", blacklistedToken.getToken());
    }


}