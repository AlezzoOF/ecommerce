package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, String> {
    List<DetalleOrden> findAllByOrden_id(String id);
}
