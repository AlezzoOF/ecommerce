package com.idos.apk.backend.tienda.tatuajes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    Usuario usuario;
    List<Orden> ordenes = new ArrayList<>();
    @BeforeEach
    public void setup(){
        Orden orden =  Orden.builder()
                .id("prueba")
                .total(22.2)
                .build();
        Orden orden2 =  Orden.builder()
                .id("prueba2")
                .total(22.2)
                .build();
        ordenes.add(orden);
        ordenes.add(orden2);

        usuario = Usuario.builder()
                .id("IdUsuario")
                .email("alejandro@gmail")
                .rol("ADMIN")
                .pwd("1234")
                .apellido("Martinez")
                .direccion("Cuba")
                .nombre("Alejandro")
                .ordenes(ordenes).build();
    }
    @Test
    void testGetters(){
        assertEquals("IdUsuario", usuario.getId() );
        assertEquals("alejandro@gmail", usuario.getEmail());
        assertEquals("ADMIN", usuario.getRol());
        assertEquals("1234", usuario.getPwd());
        assertEquals("Martinez", usuario.getApellido());
        assertEquals("Cuba", usuario.getDireccion());
        assertEquals("Alejandro", usuario.getNombre());
        assertEquals(ordenes, usuario.getOrdenes());

    }

    @Test
    void testConstructor(){
        Usuario usuario1 = new Usuario("IdUsuario","Alejandro",
                "Martinez", "Cuba","alejandro@gmail",
                "1234",true,ordenes,"USER" );

        assertEquals("IdUsuario", usuario1.getId() );
        assertEquals("alejandro@gmail", usuario1.getEmail());
        assertEquals("USER", usuario1.getRol());
        assertEquals("1234", usuario1.getPwd());
        assertEquals("Martinez", usuario1.getApellido());
        assertEquals("Cuba", usuario1.getDireccion());
        assertEquals("Alejandro", usuario1.getNombre());
        assertEquals(ordenes, usuario1.getOrdenes());


    }

    @Test
    void testSetters(){
        usuario.setId("NuevoId");
        usuario.setNombre("NuevoNombre");
        usuario.setApellido("NuevoApellido");
        usuario.setEmail("NuevoEmail");
        usuario.setDireccion("NuevaDireccion");
        usuario.setPwd("NuevaPWD");
        usuario.setEnable(false);
        usuario.setRol("NuevoRol");
        usuario.setOrdenes(null);

        assertEquals("NuevoId", usuario.getId() );
        assertEquals("NuevoEmail", usuario.getEmail());
        assertEquals("NuevoRol", usuario.getRol());
        assertEquals("NuevaPWD", usuario.getPwd());
        assertEquals("NuevoApellido", usuario.getApellido());
        assertEquals("NuevaDireccion", usuario.getDireccion());
        assertEquals("NuevoNombre", usuario.getNombre());
        assertNull(usuario.getOrdenes());
        assertEquals(false, usuario.getEnable());

    }

}