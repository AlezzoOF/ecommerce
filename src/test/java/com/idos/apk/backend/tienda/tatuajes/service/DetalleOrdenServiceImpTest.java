package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.mapper.DetalleMapper;
import com.idos.apk.backend.tienda.tatuajes.model.*;
import com.idos.apk.backend.tienda.tatuajes.repository.BlackListRepo;
import com.idos.apk.backend.tienda.tatuajes.repository.DetalleOrdenRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.OrdenRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.ProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    DetalleOrdenService service;

    DetalleOrdenInDto detalleOrdenInDto;

    DetalleOrdenOutDto detalleOrdenOutDto;

    DetalleOrden detalleOrden;

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

        Producto producto = Producto.builder()
                .cantidad(2)
                .descripcion("des")
                .tipo(tipoProducto)
                .img("Imagen")
                .precio(2.2)
                .nombre("Producto")
                .build();

       Orden orden = Orden.builder()
                .detalle(List.of(detalleOrden))
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
}