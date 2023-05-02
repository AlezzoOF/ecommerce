package com.idos.apk.backend.tienda.tatuajes.model.mapper.producto;

import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.enums.TipoProducto;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.Mapper;

public class ProductoDTOInToProducto implements Mapper<ProductoDTOIn, Producto> {
    @Override
    public Producto map(ProductoDTOIn in) {
        Producto p =new Producto();
        p.setNombre(in.nombre());
        p.setDescripcion(in.descripcion());
        p.setCantidad(in.cantidad());
        p.setImg(in.img());
        p.setPrecio(in.precio());
        p.setTipo(TipoProducto.valueOf(in.tipo()));
        return p;
    }
}
