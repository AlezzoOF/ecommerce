package com.idos.apk.backend.tienda.tatuajes.mapper;

import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrdenMapper {
    OrdenMapper INSTANCE = Mappers.getMapper(OrdenMapper.class);

    Orden ordenInToOrden(OrdenInDto ordenInDto);

    @Mapping(target="numero", source="id")
    OrdenOutDto ordenToOrdenOut(Orden orden);
}
