package com.idos.apk.backend.tienda.tatuajes.repository;


import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrdenRepository extends JpaRepository<Orden, String> {
    List<Orden> findAllByUsuario_id(String id);

    List<Orden> findAllByMesAndAgno(int mes, String anio);

}
