package com.idos.apk.backend.tienda.tatuajes.model.mapper.producto;

import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductoToProductoDTOOut implements Mapper<Producto, ProductoDTOOut> {

    @Override
    public ProductoDTOOut map(Producto in) {
//        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/mediafiles/" + in.getImg()) // Ruta relativa a la foto
//                .toUriString();
        ProductoDTOOut p = new ProductoDTOOut(in.getId(), in.getNombre(), in.getDescripcion(), in.getPrecio(), in.getCantidad(), in.getImg());
        return p;
    }

}

