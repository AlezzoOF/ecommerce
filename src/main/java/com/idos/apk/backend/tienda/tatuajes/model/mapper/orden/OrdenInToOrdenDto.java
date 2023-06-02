package com.idos.apk.backend.tienda.tatuajes.model.mapper.orden;

import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OrdenInToOrdenDto implements Mapper<Orden, OrdenDtoOut> {
    @Override
    public OrdenDtoOut map(Orden in) {
        OrdenDtoOut nueva = new OrdenDtoOut(in.getTotal(), in.getId(), in.getFechaCreacion());
        return nueva;
    }
}
