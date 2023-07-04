package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.TipoProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
    //    List<Producto> findAllByTipo(TipoProducto tipo);
    boolean existsByNombre(String email);


    Page<Producto> findByTipo(Pageable pageable, TipoProducto tipoProducto);

    Page<Producto> findByEnable(Pageable pageable, boolean bol);

    Page<Producto> findByPrecioBetween(Pageable pageable, double precioMinimo, double precioMaximo);
}
