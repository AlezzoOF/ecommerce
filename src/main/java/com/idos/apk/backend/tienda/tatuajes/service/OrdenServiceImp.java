package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.exceptions.OrdenNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.DetalleOrden;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.detalleorden.DetalleOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.orden.OrdenDtoInInToOrden;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.orden.OrdenInToOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.repository.OrdenRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.ProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.OrdenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenServiceImp implements OrdenService {

    private final OrdenRepository repository;
    private final DetalleOrdenService serviceDetalle;
    private final OrdenInToOrdenDto mapper;
    private final OrdenDtoInInToOrden mapper2;
    private final ProductoRepository productoRepository;

    public OrdenServiceImp(OrdenRepository repository, DetalleOrdenService serviceDetalle, OrdenInToOrdenDto mapper, OrdenDtoInInToOrden mapper2, ProductoRepository productoRepository) {
        this.repository = repository;
        this.serviceDetalle = serviceDetalle;
        this.mapper = mapper;
        this.mapper2 = mapper2;
        this.productoRepository = productoRepository;
    }


    @Override
    public OrdenDtoOut save(OrdenDtoIn objeto) {
        Orden nueva = mapper2.map(objeto);
        nueva.setNumero("1234121515123");
        nueva.setUsuario(new Usuario());//setear usuario
        nueva.setFechaCreacion(new Date(System.currentTimeMillis()));
        Orden guardada = repository.save(nueva);
        for (DetalleOrdenDto d :
                objeto.lista()) {
            serviceDetalle.save(d, guardada, productoRepository.findById(d.producto_id()).get());
        }
        return mapper.map(nueva);
    }

    @Override
    public List<OrdenDtoOut> getAllByUser(Long id) {
        List<Orden> lista = repository.findAllByUsuario(id);
        List<OrdenDtoOut> enviar = lista.stream().map(p -> mapper.map(p)).collect(Collectors.toList());
        return enviar;
    }

    @Override
    public void delete(Long id) {
        Orden nueva = repository.findById(id).orElseThrow(()-> new OrdenNotFoundException("Orden no encontrada"));
        repository.deleteById(id);
    }
}
