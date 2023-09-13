package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.exceptions.ProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.mapper.DetalleMapper;
import com.idos.apk.backend.tienda.tatuajes.model.*;
import com.idos.apk.backend.tienda.tatuajes.repository.BlackListRepo;
import com.idos.apk.backend.tienda.tatuajes.repository.DetalleOrdenRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.OrdenRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.ProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
class DetalleOrdenServiceImpTest {
    @Mock
    DetalleOrdenRepository repository;
    @Mock
    OrdenRepository ordenRepository;
    @Mock
    ProductoRepository productoRepository;
    @Mock
    DetalleMapper mapper;

    @InjectMocks
    DetalleOrdenServiceImp service;

    DetalleOrdenInDto detalleOrdenInDto;

    DetalleOrdenOutDto detalleOrdenOutDto;

    DetalleOrden detalleOrden;
    Orden orden;
    Producto producto;

    @BeforeEach
    void setUp() {
        Usuario usuario = Usuario.builder()
                .nombre("Usuario")
                .pwd("Usuario")
                .email("Usuario@Usuario.com")
                .rol("Usuario")
                .apellido("Usuario")
                .direccion("Usuario").build();

        TipoProducto tipoProducto = TipoProducto.builder()
                .name("Tipo").build();

        producto = Producto.builder()
                .id("Producto")
                .cantidad(2222)
                .descripcion("des")
                .tipo(tipoProducto)
                .img("Imagen")
                .precio(2.2)
                .nombre("Producto")
                .build();

       orden = Orden.builder()
               .id("orden")
                .total(2.2)
                .usuario(usuario).build();
       detalleOrdenInDto = DetalleOrdenInDto.builder()
                .cantidad(22)
                .id_producto("Producto")
                .total(2.2)
                .build();

       detalleOrden = DetalleOrden.builder()
                .id("idDetalle")
                .cantidad(22)
                .total(2.2)
                .orden(orden)
                .producto(producto)
                .build();
    }


    @Test
    void testSaveMethod(){
        when(productoRepository.findById("Producto")).thenReturn(Optional.of(producto));
        when(productoRepository.getReferenceById("Producto")).thenReturn(producto);
        when(ordenRepository.getReferenceById("orden")).thenReturn(orden);
        when(mapper.detalleInToDetalle(detalleOrdenInDto)).thenReturn(detalleOrden);

        DetalleOrdenInDto b = service.save(detalleOrdenInDto, "orden");

        // Assert
        verify(repository, times(1)).save(any());
        assertAll(
                ()-> assertNotNull(b),
                ()-> assertEquals(detalleOrdenInDto.getTotal(), b.getTotal()),
                ()-> assertEquals(detalleOrdenInDto.getId_producto(), b.getId_producto())
        );
        log.info("Save Test Pass");
    }
    @Test
    void testSaveMethodProductoNotFoundException(){
        doThrow(new ProductoNotFoundException("Produto no encontrado")).when(productoRepository).findById("Producto");


        // Assert

        assertAll(
                ()-> assertThrows(ProductoNotFoundException.class, () -> service.save(detalleOrdenInDto, "orden" ))
        );

        log.info("SaveProductNotFoundException Test Pass");
    }
}