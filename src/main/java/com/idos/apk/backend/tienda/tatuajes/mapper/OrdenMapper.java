package com.idos.apk.backend.tienda.tatuajes.mapper;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.model.DetalleOrden;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrdenMapper {

    DetalleMapper mapper = Mappers.getMapper(DetalleMapper.class);
    @Mapping(target = "detalle", source = "lista", qualifiedByName = "mapDetalleInToDetalle")
    Orden ordenInToOrden(OrdenInDto ordenInDto);

    @Mapping(target="numero", source="id")
    @Mapping(target = "id_creador", source = "usuario", qualifiedByName = "mapUsuarioToString")
    @Mapping(target = "detalle", source = "detalle", qualifiedByName = "mapDetalleToDetalleDto")
    OrdenOutDto ordenToOrdenOut(Orden orden);



    @Named("mapUsuarioToString")
    default String mapUsuarioToString(Usuario user) {
        return user != null ? user.getId() : null;
    }

    @Named("mapDetalleToDetalleDto")
    default List<DetalleOrdenOutDto> mapDetalleToDetalleDto(List<DetalleOrden> detalleOrdens) {
        return  detalleOrdens.stream().
                map(mapper::detalleToDetalleOutDto)
                .collect(Collectors.toList());

    }

    @Named("mapDetalleInToDetalle")
    default List<DetalleOrden> mapDetalleInToDetalle(List<DetalleOrdenInDto> detalleOrdens) {
        return  detalleOrdens.stream().
                map(mapper::detalleInToDetalle)
                .collect(Collectors.toList());

    }
}
