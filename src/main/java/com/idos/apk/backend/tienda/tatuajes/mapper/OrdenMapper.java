package com.idos.apk.backend.tienda.tatuajes.mapper;

import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrdenMapper {
    OrdenMapper INSTANCE = Mappers.getMapper(OrdenMapper.class);

    Orden ordenInToOrden(OrdenInDto ordenInDto);

    @Mapping(target="numero", source="id")
    @Mapping(target = "id_creador", source = "usuario", qualifiedByName = "mapUsuarioToString")
    OrdenOutDto ordenToOrdenOut(Orden orden);


    @Named("mapUsuarioToString")
    default String mapUsuarioToString(Usuario user) {
        return user != null ? user.getId() : null;
    }
}
