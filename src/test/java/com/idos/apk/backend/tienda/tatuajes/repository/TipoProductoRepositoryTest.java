package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TipoProductoRepositoryTest {

    @Autowired
    TipoProductoRepository repo;

    TipoProducto tipoProducto;
    TipoProducto tipoProducto2;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        tipoProducto = TipoProducto.builder()
                .name("Tipo1").build();
        tipoProducto = repo.save(tipoProducto);

        tipoProducto2 = TipoProducto.builder()
                .name("Tipo2").build();
    }

    @Test
    void testSaveMethod(){
        TipoProducto p = repo.save(tipoProducto2);

        assertAll(
                ()-> assertNotNull(p),
                ()-> assertEquals(tipoProducto2.getName(), p.getName())
        );

        log.info("Save Test Pass");

    }

    @Test
    void testFindAll(){
        List<TipoProducto> list = repo.findAll();

        assertAll(
                ()-> assertNotNull(list),
                ()-> assertEquals(tipoProducto.getId(), list.get(0).getId()),
                ()-> assertEquals(1, list.size()),
                ()-> assertEquals(tipoProducto.getName(), list.get(0).getName())
        );
        log.info("Find All Test Pass");
    }

    @Test
    void testDeleteByIdMethodAndFindByIdNotFound(){
        repo.deleteById(tipoProducto.getId());

        assertAll(
                ()-> assertEquals(Optional.empty(), repo.findById(tipoProducto.getId())),
                ()-> assertEquals(0, repo.findAll().size())

        );
        log.info("DeleteById and FindByIdNotFound Test Pass");

    }

    @Test
    void testFindByIdFoundMethod(){
        Optional<TipoProducto> a = repo.findById(tipoProducto.getId());

        assertAll(
                ()-> assertTrue(a.isPresent()),
                ()-> assertEquals(tipoProducto, a.get())
        );
        log.info("FindByIdFound Test Pass");

    }

    @Test
    void testExistsByIdFoundMethod(){

        boolean a = repo.existsById(tipoProducto.getId());

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


    //////////////////////////////////////////////////////////////////////////////////////////
    @Test
    void testFindByNameFoundMethod(){
        Optional<TipoProducto> a = repo.findByName(tipoProducto.getName());

        assertAll(
                ()-> assertTrue(a.isPresent()),
                ()-> assertEquals(tipoProducto, a.get())
        );
        log.info("FindByIdFound Test Pass");

    }


    @Test
    void testFindByIdNotFound(){
        Optional<TipoProducto> a = repo.findByName("error");
        assertAll(
                ()-> assertEquals(Optional.empty(), a)
        );
        log.info("DeleteById and FindByIdNotFound Test Pass");

    }
}