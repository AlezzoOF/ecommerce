package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.ProductoPageableResponse;

import java.util.List;
import java.util.Optional;

public interface ProductoService{
    ProductoDTOOut save(ProductoDTOIn objeto);
    ProductoPageableResponse getAll(int pageNo, int pageSize);
    ProductoDTOOut getById(Long id);
    ProductoDTOOut update(ProductoDTOIn producto, Long id);
    void delete(Long id);

}
