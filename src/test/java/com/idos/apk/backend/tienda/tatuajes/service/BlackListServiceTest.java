package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.model.BlacklistedToken;
import com.idos.apk.backend.tienda.tatuajes.repository.BlackListRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class BlackListServiceTest {
    @Mock
    BlackListRepo repository;
    @InjectMocks
    BlackListService service;
    BlacklistedToken blacklistedToken;


    @BeforeEach
    void setUp() {
        blacklistedToken = BlacklistedToken.builder()
                .id("id")
                .token("Token")
                .expirationDate(new Date(2000, Calendar.MARCH, 12))
                .build();
    }

    @Test
    @DisplayName("Junit test for save BlackListToken")
    void testSaveMethod(){
        // Configurar el comportamiento del repositorio mock
        when(repository.save(blacklistedToken)).thenReturn(blacklistedToken);

        // Llamar al método
        BlacklistedToken b = service.save(blacklistedToken);


        // Assert
        verify(repository, times(1)).save(any());

        assertAll(
                ()-> assertNotNull(b),
                ()-> assertEquals(blacklistedToken.getToken(), b.getToken()),
                ()-> assertEquals(blacklistedToken.getExpirationDate(), b.getExpirationDate())
        );

        log.info("Save Test Pass");
    }

    @Test
    @DisplayName("Junit test for save BlackListToken")
    void testExistMethod(){
        // Configurar el comportamiento del repositorio mock
        when(repository.existsByToken(blacklistedToken.getToken())).thenReturn(true);

        // Llamar al método
        boolean b = service.exist(blacklistedToken.getToken());

        // Assert
        verify(repository, times(1)).existsByToken(any());

        assertAll(
                ()-> assertTrue(b)
        );

        log.info("ExistsByToken Test Pass");
    }


    @Test
    @DisplayName("Junit test for save BlackListToken")
    void testDeleteExpiredTokenMethod(){

        // Crear una fecha actual
        Date currentDate = new Date();

        // Crear tokens vencidos y no vencidos
        BlacklistedToken tokenVencido1 = new BlacklistedToken();
        tokenVencido1.setId("1");
        tokenVencido1.setExpirationDate(new Date(currentDate.getTime() - 1000)); // Un segundo atrás
        BlacklistedToken tokenVencido2 = new BlacklistedToken();
        tokenVencido2.setId("2");
        tokenVencido2.setExpirationDate(new Date(currentDate.getTime() - 2000)); // Dos segundos atrás
        BlacklistedToken tokenNoVencido = new BlacklistedToken();
        tokenNoVencido.setId("3");
        tokenNoVencido.setExpirationDate(new Date(currentDate.getTime() + 1000)); // Un segundo en el futuro

        // Crear una lista de tokens que simula la respuesta de findAll()
        List<BlacklistedToken> tokens = new ArrayList<>();
        tokens.add(tokenVencido1);
        tokens.add(tokenVencido2);
        tokens.add(tokenNoVencido);

        // Configurar el comportamiento del repositorio mock
        when(repository.findAll()).thenReturn(tokens);

        // Llamar al método
        service.deleteExpiredToken();

        // Assert
        verify(repository, times(1)).findAll();
        verify(repository, times(2)).deleteById(any());

        log.info("DeleteExpiredToken Test Pass");
    }

}