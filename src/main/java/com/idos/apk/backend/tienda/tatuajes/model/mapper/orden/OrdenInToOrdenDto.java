package com.idos.apk.backend.tienda.tatuajes.model.mapper.orden;

import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrdenInToOrdenDto implements Mapper<Orden, OrdenDtoOut> {
    @Override
    public OrdenDtoOut map(Orden in) {
        OrdenDtoOut nueva = new OrdenDtoOut(in.getTotal(),new ArrayList<>(), in.getNumero(), in.getFechaCreacion());
        return nueva;
    }
}
