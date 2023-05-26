package com.idos.apk.backend.tienda.tatuajes.model.mapper.producto;

import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductoDTOInToProducto implements Mapper<ProductoDTOIn, Producto> {
    @Override
    public Producto map(ProductoDTOIn in) {
        Producto p = new Producto();
        p.setNombre(in.nombre());
        p.setDescripcion(in.descripcion());
        p.setCantidad(in.cantidad());
        p.setPrecio(in.precio());
        return p;
    }
}
