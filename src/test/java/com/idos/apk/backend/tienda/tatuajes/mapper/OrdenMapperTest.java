package com.idos.apk.backend.tienda.tatuajes.mapper;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.model.DetalleOrden;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrdenMapperTest {

    private final OrdenMapper mapper = Mappers.getMapper(OrdenMapper.class);
    List<DetalleOrdenInDto>detalleOrdenList = new ArrayList<>();
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

        Producto producto2 = Producto.builder()
                .id("Prueba2")
                .tipo(TipoProducto.builder().name("pruebaTipo2").build())
                .img("Prueba IMG2").build();
        DetalleOrdenInDto detalleOrden1 = DetalleOrdenInDto.builder()
                .id_producto("PruebaP1")
                .total(22.2)
                .cantidad(22)
                .build();


        DetalleOrdenInDto detalleOrden2 = DetalleOrdenInDto.builder()
                .id_producto("PruebaP2")
                .total(22.2)
                .cantidad(22)
                .build();


        detalleOrdenList.add(detalleOrden2);
        detalleOrdenList.add(detalleOrden1);

    }

    @Test
    void ordenInToOrden() {

        OrdenInDto ordenInDto = OrdenInDto.builder()
                .total(12.2)
                .token("prueba")
                .lista(detalleOrdenList)
                .build();

        Orden orden = mapper.ordenInToOrden(ordenInDto);

        assertNotNull(orden);
        assertNotNull(orden.getDetalle());
        assertEquals(ordenInDto.getTotal(), orden.getTotal());



    }

    @Test
    void ordenToOrdenOut() {
//        OrdenOutDto ordenOutDto = mapper.ordenToOrdenOut(orden);
//
//        assertNotNull(ordenOutDto);
//        assertNotNull(ordenOutDto.getDetalle());
//        assertEquals(orden.getTotal(), ordenOutDto.getTotal());
    }
}