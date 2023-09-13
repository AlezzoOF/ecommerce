package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.dto.producto.FiltroProducto;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductoRepositoryTest {
    @Autowired
    ProductoRepository repo;
    @Autowired
    TipoProductoRepository tipoProductoRepository;

    Producto producto;
    Producto producto1;
    @BeforeEach
    void setUp() {
        repo.deleteAll();
        TipoProducto tipoProducto = TipoProducto.builder()
                .name("Tipo").build();
        tipoProducto = tipoProductoRepository.save(tipoProducto);

        TipoProducto tipoProducto2 = TipoProducto.builder()
                .name("Tipo2").build();
        tipoProducto2 = tipoProductoRepository.save(tipoProducto2);

        producto1 = Producto.builder()
                .cantidad(2)
                .descripcion("des")
                .tipo(tipoProducto2)
                .img("Imagen")
                .precio(2.2)
                .nombre("Producto")
                .build();

        producto = Producto.builder()
                .cantidad(2)
                .descripcion("des")
                .tipo(tipoProducto)
                .img("Imagen")
                .precio(2.2)
                .nombre("Producto")
                .build();
        producto = repo.save(producto);
    }

    @Test
    void testSaveMethod(){
        Producto b = repo.save(producto1);

        assertAll(
                ()-> assertNotNull(b),
                ()-> assertEquals(producto1.getNombre(), b.getNombre()),
                ()-> assertEquals(producto1.getTipo(), b.getTipo()),
                ()-> assertEquals(producto1.getImg(), b.getImg()),
                ()-> assertEquals(producto1.getPrecio(), b.getPrecio())
        );

        log.info("Save Test Pass");

    }

    @Test
    void testFindAll(){
        List<Producto> list = repo.findAll();

        assertAll(
                ()-> assertNotNull(list),
                ()-> assertEquals(producto.getId(), list.get(0).getId()),
                ()-> assertEquals(1, list.size()),
                ()-> assertEquals(producto.getTipo(), list.get(0).getTipo()),
                ()-> assertEquals(producto.getDescripcion(), list.get(0).getDescripcion())
        );
        log.info("Find All Test Pass");
    }

    @Test
    void testDeleteByIdMethodAndFindByIdNotFound(){
        repo.deleteById(producto.getId());

        assertAll(
                ()-> assertEquals(Optional.empty(), repo.findById(producto.getId())),
                ()-> assertEquals(0, repo.findAll().size())

        );
        log.info("DeleteById and FindByIdNotFound Test Pass");

    }

    @Test
    void testFindByIdFoundMethod(){
        Optional<Producto> a = repo.findById(producto.getId());

        assertAll(
                ()-> assertTrue(a.isPresent()),
                ()-> assertEquals(producto, a.get())
        );
        log.info("FindByIdFound Test Pass");

    }

    @Test
    void testExistsByIdFoundMethod(){

        boolean a = repo.existsById(producto.getId());

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

    ///////////////////////////////////////////////////////////////////


    @Test
    void testFindAllFilter(){
        Specification<Producto> spec = null;
        Pageable pageable = Pageable.unpaged();
        Page<Producto> resultado = repo.findAll(spec, pageable);


        assertAll(
                ()-> assertNotNull(resultado),
                ()-> assertEquals(producto.getId(), resultado.toList().get(0).getId()),
                ()-> assertEquals(1, resultado.toList().size()),
                ()-> assertEquals(producto.getTipo(), resultado.toList().get(0).getTipo()),
                ()-> assertEquals(producto.getDescripcion(), resultado.toList().get(0).getDescripcion())
        );
        log.info("Find All Test Pass");
    }


}