package com.idos.apk.backend.tienda.tatuajes.model.mapper.producto;

import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.Mapper;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.StorageService;
import org.springframework.stereotype.Component;

@Component
public class ProductoToProductoDTOOut implements Mapper<Producto, ProductoDTOOut> {

    @Override
    public ProductoDTOOut map(Producto in) {
        ProductoDTOOut p = new ProductoDTOOut(in.getId(), in.getNombre(),in.getDescripcion(), in.getPrecio(), in.getCantidad(), in.getImg());
        return p;
    }

}

