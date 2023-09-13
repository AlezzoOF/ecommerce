package com.idos.apk.backend.tienda.tatuajes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoProductoTest {
    TipoProducto tipoProducto;
    @BeforeEach
    public void setup(){

        tipoProducto = TipoProducto.builder()
                .id("id")
                .name("name").build();
    }

    @Test
    void testGetters(){
        assertNotNull(tipoProducto);
        assertEquals("id", tipoProducto.getId());
        assertEquals("name", tipoProducto.getName());
    }

    @Test
    void testConstructor(){
        TipoProducto tipoProducto1 = new TipoProducto("id2", "nombre");
        assertEquals("id2", tipoProducto1.getId());
        assertEquals("nombre", tipoProducto1.getName());

    }

    @Test
    void testSetters(){
        tipoProducto.setId("21");
        tipoProducto.setName("Nuevo");

        assertEquals("21", tipoProducto.getId());
        assertEquals("Nuevo", tipoProducto.getName());
    }

}