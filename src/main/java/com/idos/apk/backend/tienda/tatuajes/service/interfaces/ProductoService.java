package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoPageableResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ProductoService {
    ProductoDTOOut save(ProductoDTOIn objeto, MultipartFile file, HttpServletRequest request);


    ProductoPageableResponse getAll(int pageNo, int pageSize);

    ProductoDTOOut getById(String id) throws RuntimeException;

    ProductoDTOOut update(ProductoDTOIn producto, String id, HttpServletRequest request);

    void delete(String id);

    ProductoPageableResponse getAllByTipo(int pageNo, int pageSize, String tipo);

    ProductoPageableResponse findAllByEnable(boolean bol, int pageNo, int pageSize);//Buscar los productos en stock

    ProductoPageableResponse findByPrecioBetween(int pageNo, int pageSize, double precioMinimo, double precioMaximo);


}
