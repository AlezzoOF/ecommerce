package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
