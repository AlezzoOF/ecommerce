package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.dto.producto.FiltroProducto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoOutDto;
import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoPageableResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ProductoService {
    ProductoOutDto save(ProductoInDto objeto, MultipartFile file, HttpServletRequest request);


    ProductoPageableResponse getAll(int pageNo, int pageSize);

    ProductoOutDto getById(String id) throws RuntimeException;

    ProductoOutDto update(ProductoInDto producto, String id, HttpServletRequest request);

    void delete(String id, HttpServletRequest request );

//    ProductoPageableResponse getAllByTipo(int pageNo, int pageSize, String tipo);
//
//    ProductoPageableResponse findAllByEnable(boolean bol, int pageNo, int pageSize);//Buscar los productos en stock
//
//    ProductoPageableResponse findByPrecioBetween(int pageNo, int pageSize, double precioMinimo, double precioMaximo);

    ///prueba

    ProductoPageableResponse filtrarProductos(FiltroProducto filtro, int pageNo, int pageSize);


}
