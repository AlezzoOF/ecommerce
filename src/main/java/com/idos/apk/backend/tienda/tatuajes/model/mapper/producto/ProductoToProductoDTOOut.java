package com.idos.apk.backend.tienda.tatuajes.model.mapper.producto;

import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.Mapper;

public class ProductoToProductoDTOOut implements Mapper<Producto, ProductoDTOOut> {
    @Override
    public ProductoDTOOut map(Producto in) {
        ProductoDTOOut p = new ProductoDTOOut(in.getNombre(), in.getDescripcion(), in.getImg(), in.getPrecio(), in.isEnable(),in.getId());
        return p;
    }
}
