package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;

import java.util.List;

public interface TipoProductoService {
    TipoProducto save(String string);

    List<TipoProducto> findAll();

    void delete(String id);

    boolean ifExist(String s);
}
