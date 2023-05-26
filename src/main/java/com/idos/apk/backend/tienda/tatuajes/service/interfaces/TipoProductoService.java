package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;

import java.util.List;

public interface TipoProductoService {
    void save(String string);
    List<TipoProducto> findAll();
    void delete(Long id);
    boolean ifExist(String s);
}
