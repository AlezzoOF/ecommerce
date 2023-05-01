package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.Producto;

import java.util.List;
import java.util.Optional;

public interface GenericService<E>{
    E save(E objeto);
    Optional<E> get(Long id);
    void update(E objeto);
    void delete(Long id);
    List<E> findAll();


}
