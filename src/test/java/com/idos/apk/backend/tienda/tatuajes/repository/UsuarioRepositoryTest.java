package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository repo;

    Usuario usuario;
    Usuario usuario2;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        usuario = Usuario.builder()
                .nombre("Usuario")
                .pwd("Usuario")
                .email("Usuario@Usuario.com")
                .rol("Usuario")
                .apellido("Usuario")
                .direccion("Usuario").build();
        usuario = repo.save(usuario);

        usuario2 = Usuario.builder()
                .nombre("Usuario2")
                .pwd("Usuario2")
                .email("Usuario2@Usuario.com")
                .rol("Usuario2")
                .apellido("Usuario2")
                .direccion("Usuario2").build();

    }


    @Test
    void testSaveMethod(){
        Usuario b = repo.save(usuario2);

        assertAll(
                ()-> assertNotNull(b),
                ()-> assertEquals(usuario2.getRol(), b.getRol()),
                ()-> assertEquals(usuario2.getPwd(), b.getPwd()),
                ()-> assertEquals(usuario2.getNombre(), b.getNombre()),
                ()-> assertEquals(usuario2.getApellido(), b.getApellido()),
                ()-> assertEquals(usuario2.getDireccion(), b.getDireccion()),
                ()-> assertEquals(usuario2.getEmail(), b.getEmail())
        );

        log.info("Save Test Pass");

    }

    @Test
    void testFindAll(){
        List<Usuario> list = repo.findAll();

        assertAll(
                ()-> assertNotNull(list),
                ()-> assertEquals(usuario.getId(), list.get(0).getId()),
                ()-> assertEquals(1, list.size()),
                ()-> assertEquals(usuario.getEmail(), list.get(0).getEmail()),
                ()-> assertEquals(usuario.getPwd(), list.get(0).getPwd())
        );
        log.info("Find All Test Pass");
    }

    @Test
    void testDeleteByIdMethodAndFindByIdNotFound(){
        repo.deleteById(usuario.getId());

        assertAll(
                ()-> assertEquals(Optional.empty(), repo.findById(usuario.getId())),
                ()-> assertEquals(0, repo.findAll().size())

        );
        log.info("DeleteById and FindByIdNotFound Test Pass");

    }

    @Test
    void testFindByIdFoundMethod(){
        Optional<Usuario> a = repo.findById(usuario.getId());

        assertAll(
                ()-> assertTrue(a.isPresent()),
                ()-> assertEquals(usuario, a.get())
        );
        log.info("FindByIdFound Test Pass");

    }

    @Test
    void testExistsByIdFoundMethod(){

        boolean a = repo.existsById(usuario.getId());

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
    /////////////////////////////////////////////////////////////////////////////////////

    @Test
    void testFindByEmail(){
        Optional<Usuario> list = repo.findByEmail(usuario.getEmail());

        assertAll(
                ()-> assertTrue(list.isPresent()),
                ()-> assertEquals(usuario, list.get())
        );
        log.info("Find All Test Pass");
    }


    @Test
    void testExistsByEmailFoundMethod(){

        boolean a = repo.existsByEmail(usuario.getEmail());

        assertAll(
                ()-> assertTrue(a)
        );
        log.info("ExistsByIdFound Test Pass");

    }
}