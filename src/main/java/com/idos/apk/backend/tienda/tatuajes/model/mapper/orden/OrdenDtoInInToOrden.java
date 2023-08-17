package com.idos.apk.backend.tienda.tatuajes.model.mapper.orden;

import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoIn;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OrdenDtoInInToOrden implements Mapper<OrdenDtoIn, Orden> {
    @Override
    public Orden map(OrdenDtoIn in) {
        return Orden.builder()
                .total(in.total()).build();
    }
}
