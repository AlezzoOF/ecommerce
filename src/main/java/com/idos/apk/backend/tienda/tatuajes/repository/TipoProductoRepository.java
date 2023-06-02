package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, String> {
    boolean existsByName(String string);

    Optional<TipoProducto> findByName(String string);
}
