package com.idos.apk.backend.tienda.tatuajes.mapper;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.model.DetalleOrden;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DetalleMapperTest {
    private final DetalleMapper mapper = Mappers.getMapper(DetalleMapper.class);
    @Test
    void detalleInToDetalle() {
        DetalleOrdenInDto detalleOrdenInDto = DetalleOrdenInDto.builder()
                .id_producto("prueba")
                .cantidad(44)
                .total(32.4)
                .build();

        DetalleOrden detalleOrden = mapper.detalleInToDetalle(detalleOrdenInDto);

        assertNotNull(detalleOrden);
        assertEquals(detalleOrdenInDto.getId_producto(), detalleOrden.getProducto().getId());
        assertEquals(detalleOrdenInDto.getCantidad(), detalleOrden.getCantidad());
        assertEquals(detalleOrdenInDto.getTotal(), detalleOrden.getTotal());
    }

    @Test
    void detalleToOneDetalleDto() {
        DetalleOrden detalleOrden = DetalleOrden.builder()
                .id("prueba")
                .orden(Orden.builder().id("prueba").build())
                .producto(Producto.builder().id("prueba").tipo(TipoProducto.builder().name("prueba").build()).build())
                .total(22.2)
                .cantidad(22)
                .build();
        DetalleOrdenOutDto detalleOrdenOutDto = mapper.detalleToDetalleOutDto(detalleOrden);

        assertNotNull(detalleOrdenOutDto);
        assertEquals(detalleOrden.getCantidad(), detalleOrdenOutDto.getCantidad());
        assertEquals(detalleOrden.getProducto().getId(), detalleOrdenOutDto.getProducto().getId());
        assertEquals(detalleOrden.getProducto().getTipo().getName(), detalleOrdenOutDto.getProducto().getTipo());
        assertEquals(detalleOrden.getTotal(), detalleOrdenOutDto.getTotal());
    }
}