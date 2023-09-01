package com.idos.apk.backend.tienda.tatuajes.mapper;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoOutDto;
import com.idos.apk.backend.tienda.tatuajes.model.DetalleOrden;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DetalleMapper {

    ProductoMapper mapper = Mappers.getMapper( ProductoMapper.class );

    @Mapping(target = "producto", source = "id_producto", qualifiedByName = "mapIdToProducto")
    DetalleOrden detalleInToDetalle(DetalleOrdenInDto detalleOrden);

    @Mapping(target = "producto", source = "producto", qualifiedByName = "mapProductoToProductoDto")
    DetalleOrdenOutDto detalleToDetalleOutDto(DetalleOrden detalleOrden);


    @Named("mapIdToProducto")
    default Producto mapIdToProducto(String id) {
        return Producto.builder()
                .id(id).build();
    }


    @Named("mapProductoToProductoDto")
    default ProductoOutDto mapIdToProducto(Producto producto) {
        return mapper.productoToProductoDtoOut(producto);
    }
}
