package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
