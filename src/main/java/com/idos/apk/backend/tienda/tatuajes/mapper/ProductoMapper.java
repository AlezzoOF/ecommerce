package com.idos.apk.backend.tienda.tatuajes.mapper;

import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoOutDto;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(source = "tipo", target = "tipo", qualifiedByName = "mapTipoProductoToString")
    ProductoOutDto productoToProductoDtoOut(Producto producto);
    @Mapping(source = "tipo", target = "tipo", qualifiedByName = "mapTipoStringToTipoProducto")
    Producto productoInToProducto(ProductoInDto productoInDto);

    @Named("mapTipoProductoToString")
    default String mapTipoProductoToString(TipoProducto tipoProducto) {
        return tipoProducto != null ? tipoProducto.getName() : null;
    }

    @Named("mapTipoStringToTipoProducto")
    default TipoProducto mapTipoStringToTipoProducto(String tipo) {
        // Aquí debes implementar la lógica para obtener el TipoProducto según el nombre
        // Puede ser a través de un servicio o repositorio
        // Por ejemplo:
        // return tipoProductoService.obtenerTipoProductoPorNombre(tipo);
        return TipoProducto.builder()
                .name(tipo).build(); // Cambia esto por la lógica real
    }
}
