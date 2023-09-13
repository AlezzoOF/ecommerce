package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.*;
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
class OrdenRepositoryTest {
    @Autowired
    DetalleOrdenRepository detalleOrdenRepository;
    @Autowired
    OrdenRepository repo;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TipoProductoRepository tipoProductoRepository;
    Orden orden;
    Orden orden1;

    @BeforeEach
    void setUp() {

        repo.deleteAll();

        Usuario usuario = Usuario.builder()
                .nombre("Usuario")
                .pwd("Usuario")
                .email("Usuario@Usuario.com")
                .rol("Usuario")
                .apellido("Usuario")
                .direccion("Usuario").build();
        usuario = usuarioRepository.save(usuario);


        TipoProducto tipoProducto = TipoProducto.builder()
                .name("Tipo").build();
        tipoProducto = tipoProductoRepository.save(tipoProducto);


        Producto producto = Producto.builder()
                .cantidad(2)
                .descripcion("des")
                .tipo(tipoProducto)
                .img("Imagen")
                .precio(2.2)
                .nombre("Producto")
                .build();
        producto = productoRepository.save(producto);


        DetalleOrden detalleOrden = DetalleOrden.builder()
                .cantidad(1)
                .total(1.1)
                .orden(orden)
                .producto(producto).build();
        detalleOrdenRepository.save(detalleOrden);


        orden = Orden.builder()
                .detalle(List.of(detalleOrden))
                .total(2.2)
                .usuario(usuario).build();

        orden1 = Orden.builder()
                .detalle(List.of(detalleOrden))
                .total(2.2)
                .usuario(usuario).build();
        orden = repo.save(orden);
    }


    @Test
    void testSaveMethod(){
        Orden b = repo.save(orden1);

        assertAll(
                ()-> assertNotNull(b),
                ()-> assertNotNull(b.getFechaCreacion()),
                ()-> assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")), b.getAgno()),
                ()-> assertEquals(Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM"))), b.getMes()),
                ()-> assertNotNull(repo.findById(b.getId())),
                ()-> assertEquals(orden1.getTotal(), b.getTotal()),
                ()-> assertEquals(orden1.getUsuario(), b.getUsuario()),
                ()-> assertEquals(orden1.getDetalle(), b.getDetalle())

        );

        log.info("Save Test Pass");

    }

    @Test
    void testFindAll(){
        List<Orden> list = repo.findAll();

        assertAll(
                ()-> assertNotNull(list),
                ()-> assertEquals(orden.getId(), list.get(0).getId()),
                ()-> assertEquals(1, list.size()),
                ()-> assertEquals(orden.getUsuario(), list.get(0).getUsuario()),
                ()-> assertEquals(orden.getDetalle(), list.get(0).getDetalle())
        );
        log.info("Find All Test Pass");
    }

    @Test
    void testDeleteByIdMethodAndFindByIdNotFound(){
        repo.deleteById(orden.getId());

        assertAll(
                ()-> assertEquals(Optional.empty(), repo.findById(orden.getId())),
                ()-> assertEquals(0, repo.findAll().size())

        );
        log.info("DeleteById and FindByIdNotFound Test Pass");

    }

    @Test
    void testFindByIdFoundMethod(){
        Optional<Orden> a = repo.findById(orden.getId());

        assertAll(
                ()-> assertTrue(a.isPresent()),
                ()-> assertEquals(orden, a.get())
        );
        log.info("FindByIdFound Test Pass");

    }

    @Test
    void testExistsByIdFoundMethod(){

        boolean a = repo.existsById(orden.getId());

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
    void testFindAllByUsuarioId(){
        List<Orden> list = repo.findAllByUsuario_id(orden.getUsuario().getId());

        assertAll(
                ()-> assertNotNull(list),
                ()-> assertEquals(orden.getId(), list.get(0).getId()),
                ()-> assertEquals(1, list.size()),
                ()-> assertEquals(orden.getUsuario(), list.get(0).getUsuario()),
                ()-> assertEquals(orden.getDetalle(), list.get(0).getDetalle())
        );
        log.info("Find All Test Pass");
    }

    @Test
    void testFindAllByByMesAndAgno(){
        List<Orden> list = repo.findAllByMesAndAgno(Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM"))),
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));

        assertAll(
                ()-> assertNotNull(list),
                ()-> assertEquals(orden.getId(), list.get(0).getId()),
                ()-> assertEquals(1, list.size()),
                ()-> assertEquals(orden.getUsuario(), list.get(0).getUsuario()),
                ()-> assertEquals(orden.getDetalle(), list.get(0).getDetalle())
        );
        log.info("Find All Test Pass");
    }
}