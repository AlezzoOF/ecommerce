package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoPageableResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ProductoService {
    ProductoDTOOut save(ProductoDTOIn objeto, MultipartFile file);

    Resource getFoto(String id);

    ProductoPageableResponse getAll(int pageNo, int pageSize);

    ProductoDTOOut getById(String id);

    ProductoDTOOut update(ProductoDTOIn producto, String id);

    void delete(String id);

    ProductoPageableResponse getAllByTipo(int pageNo, int pageSize, String tipo);

    ProductoPageableResponse findAllByEnable(boolean bol, int pageNo, int pageSize);//Buscar los productos en stock

    ProductoPageableResponse findByPrecioBetween(int pageNo, int pageSize, double precioMinimo, double precioMaximo);


}
