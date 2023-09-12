package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.BlacklistedToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BlackListRepoTest {
    @Autowired
    BlackListRepo repo;
    BlacklistedToken token;
    BlacklistedToken token2;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        token = BlacklistedToken.builder()
                .id("1")
                .token("Token")
                .expirationDate(new Date(2000, Calendar.MARCH, 12))
                .build();
        token2 = BlacklistedToken.builder()
                .id("2")
                .token("Token2")
                .expirationDate(new Date(2000, Calendar.MARCH, 12))
                .build();
        token = repo.save(token);
    }

    @Test
    void testSaveMethod(){
        BlacklistedToken b = repo.save(token2);


        assertAll(
                ()-> assertNotNull(b),
                ()-> assertNotNull(repo.findById(b.getId())),
                ()-> assertEquals(token2.getToken(), b.getToken()),
                ()-> assertEquals(token2.getExpirationDate(), b.getExpirationDate())
        );

        log.info("Save Test Pass");

    }

    @Test
    void testFindAll(){
        List<BlacklistedToken> list = repo.findAll();

        assertAll(
                ()-> assertNotNull(list),
                ()-> assertEquals(token.getToken(), list.get(0).getToken()),
                ()-> assertEquals(1, list.size()),
                ()-> assertEquals(token.getExpirationDate(), list.get(0).getExpirationDate())
        );
        log.info("Find All Test Pass");
    }

    @Test
    void testDeleteByIdMethodAndFindByIdNotFound(){
        repo.deleteById(token.getId());

        assertAll(
                ()-> assertEquals(Optional.empty(), repo.findById(token.getId())),
                ()-> assertEquals(0, repo.findAll().size())

        );
        log.info("DeleteById and FindByIdNotFound Test Pass");

    }

    @Test
    void testFindByIdFoundMethod(){
        Optional<BlacklistedToken> a = repo.findById(token.getId());

        assertAll(
                ()-> assertTrue(a.isPresent()),
                ()-> assertEquals(token, a.get())
        );
        log.info("FindByIdFound Test Pass");

    }

    @Test
    void testExistsByIdFoundMethod(){
        boolean a = repo.existsById(token.getId());

        assertAll(
                ()-> assertTrue(a)
        );
        log.info("ExistsByIdFound Test Pass");

    }


    @Test
    void testExistsByIdNotFoundMethod(){

        boolean a = repo.existsById("Error");

        assertAll(
                ()-> assertFalse(a)
        );
        log.info("ExistsByIdNotFound Test Pass");

    }


    ///////////Metodos expecificos del repo/////////////
    @Test
    void testExistsByTokenFoundMethod(){
        boolean a = repo.existsByToken(token.getToken());

        assertAll(
                ()-> assertTrue(a)
        );
        log.info("ExistsByTokenFound Test Pass");

    }

    @Test
    void testExistsByTokenNotFoundMethod(){
        boolean a = repo.existsByToken("Error");

        assertAll(
                ()-> assertFalse(a)
        );
        log.info("ExistsByTokenNotFound Test Pass");

    }

}