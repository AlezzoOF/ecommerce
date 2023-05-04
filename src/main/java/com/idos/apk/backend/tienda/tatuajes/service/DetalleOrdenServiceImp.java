package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.model.DetalleOrden;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.repository.DetalleOrdenRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleOrdenServiceImp implements DetalleOrdenService {
    private final DetalleOrdenRepository repository;

    public DetalleOrdenServiceImp(DetalleOrdenRepository repository) {
        this.repository = repository;
    }

    @Override
    public DetalleOrdenDto save(DetalleOrdenDto objeto, Orden orden, Producto producto) {
        DetalleOrden nuevo = new DetalleOrden();
        nuevo.setOrden(orden);
        nuevo.setCantidad(objeto.cantidad());
        nuevo.setTotal(objeto.total());
        nuevo.setProducto(producto);
        repository.save(nuevo);
        return objeto;
    }

    @Override
    public List<DetalleOrdenDto> getAllByOrden(Long id) {
        List<DetalleOrdenDto> enviar = repository.findAllByOrden(id).stream().map(p -> mapper(p)).collect(Collectors.toList());
        return enviar;
    }

    private DetalleOrdenDto mapper(DetalleOrden detalleOrden) {
        DetalleOrdenDto enviar = new DetalleOrdenDto(detalleOrden.getCantidad(), detalleOrden.getProducto().getId(), detalleOrden.getTotal());
        return enviar;
    }
}
