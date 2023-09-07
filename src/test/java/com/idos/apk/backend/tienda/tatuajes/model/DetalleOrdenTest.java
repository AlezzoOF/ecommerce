package com.idos.apk.backend.tienda.tatuajes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DetalleOrdenTest {

    DetalleOrden detalleOrden1;
    Orden orden1;
    Orden orden2;
    Producto producto;

    @BeforeEach
    public void setup(){
        orden1 = Orden.builder()
                .id("Orden1").build();
        orden2 = Orden.builder()
                .id("Orden2").build();
        producto = Producto.builder()
                .id("Producto").build();

        detalleOrden1 = DetalleOrden.builder()
                .id("Detalle1")
                .orden(orden1)
                .cantidad(1)
                .total(1.1)
                .producto(producto)
                .build();


    }

    @Test
    void testGetters(){
        assertNotNull(detalleOrden1);
        assertEquals("Detalle1", detalleOrden1.getId());
        assertEquals(orden1,detalleOrden1.getOrden());
        assertEquals(producto, detalleOrden1.getProducto());
    }

    @Test
    void testConstructor(){

        DetalleOrden detalleOrden3 = new DetalleOrden("id", 22, 22.22, orden2, producto);

        assertNotNull(detalleOrden3);
        assertEquals("id", detalleOrden3.getId());
        assertEquals(orden2,detalleOrden3.getOrden());
        assertEquals(producto, detalleOrden3.getProducto());
        assertEquals(22, detalleOrden3.getCantidad());
        assertEquals(22.22, detalleOrden3.getTotal());


    }


    @Test
    void testSetters() {
        detalleOrden1.setId("2");
        detalleOrden1.setOrden(orden2);
        detalleOrden1.setCantidad(2);
        detalleOrden1.setTotal(2.2);

        assertNotNull(detalleOrden1);
        assertEquals("2", detalleOrden1.getId());
        assertEquals(orden2,detalleOrden1.getOrden());
        assertEquals(2, detalleOrden1.getCantidad());
        assertEquals(2.2, detalleOrden1.getTotal());
    }



}