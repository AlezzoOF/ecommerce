package com.idos.apk.backend.tienda.tatuajes.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrdenTest {

    List<DetalleOrden> detalleOrdens = new ArrayList<>();
    Orden orden;
    @BeforeEach
    public void setup(){
        orden = Orden.builder()
                .id("PruebaOrden")
                .build();
        Producto producto1 = Producto.builder()
                .id("Prueba")
                .tipo(TipoProducto.builder().name("pruebaTipo").build())
                .img("Prueba IMG").build();

        DetalleOrden detalleOrden1 = DetalleOrden.builder()
                .id("PruebaP1")
                .producto(producto1)
                .orden(orden)
                .total(22.2)
                .cantidad(22)
                .build();



        detalleOrdens.add(detalleOrden1);

    }
    @Test
    void testConstructorAndGetters() {
        Orden orden =  Orden.builder()
                .id("prueba")
                .total(22.2)
                .detalle(detalleOrdens)
                .usuario(Usuario.builder().id("prueba").build())
                .build();
        orden.prePersist();

        assertEquals("prueba", orden.getId());
        assertEquals(LocalDate.now(), orden.getFechaCreacion());
        assertNotNull(orden.getAgno());
        assertNotNull(orden.getMes());
        assertEquals(22.2, orden.getTotal());
        assertNotNull(orden.getUsuario());
        assertNotNull(orden.getDetalle());
    }

    @Test
    void testSetters() {
        Orden orden =  Orden.builder()
                .id("prueba")
                .total(22.2)
                .mes(12)
                .agno("2121")
                .fechaCreacion(LocalDate.of(2023, 8, 19))
                .detalle(detalleOrdens)
                .usuario(Usuario.builder().id("prueba").build())
                .build();

        orden.setId("2");
        orden.setFechaCreacion(LocalDate.of(2023, 7, 19));
        orden.setAgno("2023");
        orden.setMes(7);
        orden.setTotal(100.0);
        orden.setUsuario(new Usuario());
        orden.setDetalle(new ArrayList<>());

        assertEquals("2", orden.getId());
        assertEquals(LocalDate.of(2023, 7, 19), orden.getFechaCreacion());
        assertEquals("2023", orden.getAgno());
        assertEquals(7, orden.getMes());
        assertEquals(100.0, orden.getTotal());
        assertNotNull(orden.getUsuario());
        assertNotNull(orden.getDetalle());
    }



}