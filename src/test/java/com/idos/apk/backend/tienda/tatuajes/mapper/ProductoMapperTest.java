package com.idos.apk.backend.tienda.tatuajes.mapper;

import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoOutDto;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductoMapperTest {
    private final ProductoMapper mapper = Mappers.getMapper(ProductoMapper.class);

    @Test
    void productoToProductoDtoOut() {
//        Producto producto = Producto.builder()
//                .id("Prueba")
//                .img("Prueba")
//                .tipo(TipoProducto.builder().id("Prueba").name("Prueba").build())
//                .precio(123.11)
//                .descripcion("Prueba")
//                .enable(true)
//                .nombre("Prueba")
//                .cantidad(22)
//                .build();
//
//        //when
//        ProductoOutDto productoOutDto = mapper.productoToProductoDtoOut( producto );
//
//        //then
//        assertNotNull(productoOutDto);
//        assertEquals("Prueba", productoOutDto.getNombre());
//        assertEquals("Prueba", productoOutDto.getId());
//        assertEquals(123.11, productoOutDto.getPrecio());
    }

    @Test
    void productoInToProducto() {
        ProductoInDto p = ProductoInDto.builder()
                .nombre("prueba")
                .precio(22.2)
                .descripcion("prueba")
                .cantidad(22)
                .tipo("Prueba")
                .build();

        Producto producto  = mapper.productoInToProducto(p);

        assertNotNull(producto);
        assertEquals("prueba", producto.getNombre());
        assertEquals("prueba", producto.getDescripcion());
        assertEquals(22.2, producto.getPrecio());
        assertEquals(22, producto.getCantidad());
        assertEquals("Prueba", producto.getTipo().getName());
        assertTrue(producto.isEnable());





    }
}