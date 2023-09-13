package com.idos.apk.backend.tienda.tatuajes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    TipoProducto tipo;
    Producto producto;
    @BeforeEach
    public void setup(){
        tipo = TipoProducto.builder()
                .id("id")
                .name("tipo").build();

        producto = Producto.builder()
                .id("id")
                .nombre("nombre")
                .descripcion("des")
                .img("img")
                .precio(22.22)
                .cantidad(2)
                .tipo(tipo)
                .build();

    }
    @Test
    void testGetters() {
        assertEquals("id", producto.getId());
        assertEquals("nombre", producto.getNombre());
        assertEquals("des", producto.getDescripcion());
        assertEquals("img", producto.getImg());
        assertEquals(22.22, producto.getPrecio());
        assertEquals(2, producto.getCantidad());
        assertEquals(tipo, producto.getTipo());


    }

    @Test
    void testConstructor(){
        Producto producto1 = new Producto("id", "nombre",
                "des", "img", 22.22, 2, tipo, true);
        assertNotNull(producto1);
        assertEquals("id", producto1.getId());
        assertEquals("nombre", producto1.getNombre());
        assertEquals("des", producto1.getDescripcion());
        assertEquals("img", producto1.getImg());
        assertEquals(2, producto1.getCantidad());
        assertEquals(22.22, producto1.getPrecio());
        assertEquals(tipo, producto1.getTipo());


    }

    @Test
    void testSetters() {

        producto.setId("2");
        producto.setNombre("Nombre2");
        producto.setDescripcion("2023");
        producto.setImg("imagen");
        producto.setCantidad(111);
        producto.setPrecio(122.122);
        producto.setEnable(false);
        producto.setTipo(null);


        assertEquals("2", producto.getId());
        assertEquals("Nombre2", producto.getNombre());
        assertEquals("2023", producto.getDescripcion());
        assertEquals("imagen", producto.getImg());
        assertEquals(111, producto.getCantidad());
        assertEquals(122.122, producto.getPrecio());
        assertNull(producto.getTipo());
        assertFalse(producto.isEnable());
    }

}