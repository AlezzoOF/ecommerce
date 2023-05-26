package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoPageableResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ProductoService {
    ProductoDTOOut save(ProductoDTOIn objeto, MultipartFile file);

    ProductoPageableResponse getAll(int pageNo, int pageSize);

    ProductoDTOOut getById(Long id);

    ProductoDTOOut update(ProductoDTOIn producto, Long id);

    void delete(Long id);

    ProductoPageableResponse getAllByTipo(int pageNo, int pageSize, String tipo);


}
