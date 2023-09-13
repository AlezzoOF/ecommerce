package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TipoProductoRepository extends JpaRepository<TipoProducto, String> {
    boolean existsByName(String string);

    Optional<TipoProducto> findByName(String string);
}
