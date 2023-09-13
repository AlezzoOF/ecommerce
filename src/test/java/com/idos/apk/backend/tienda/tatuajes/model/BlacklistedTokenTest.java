package com.idos.apk.backend.tienda.tatuajes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BlacklistedTokenTest {
    BlacklistedToken blacklistedToken;
    BlacklistedToken blacklistedToken1;


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


    }


    @Test
    void testGetters() {

        assertNotNull(blacklistedToken);
        assertEquals("prueba", blacklistedToken.getId());
        assertNotNull(blacklistedToken.getExpirationDate());
        assertEquals(new Date(2000, Calendar.MARCH, 12), blacklistedToken.getExpirationDate());
        assertEquals("prueba", blacklistedToken.getToken());
    }

    @Test
    void testConstructor(){
        BlacklistedToken blacklistedToken3 = new BlacklistedToken("id", "token", new Date());

        assertNotNull(blacklistedToken3);
        assertEquals("id", blacklistedToken3.getId());
        assertNotNull(blacklistedToken3.getExpirationDate());
        assertEquals("token", blacklistedToken3.getToken());


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