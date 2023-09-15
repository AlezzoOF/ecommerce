package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.detalle.DetalleOrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.exceptions.OrdenNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.exceptions.ProductoNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.mapper.DetalleMapper;
import com.idos.apk.backend.tienda.tatuajes.model.DetalleOrden;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.repository.DetalleOrdenRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.OrdenRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.ProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetalleOrdenServiceImp implements DetalleOrdenService {
    private final DetalleOrdenRepository repository;
    private final OrdenRepository ordenRepository;
    private final ProductoRepository productoRepository;
    private final DetalleMapper mapper;


    @Override
    @Transactional
    public DetalleOrdenInDto save(DetalleOrdenInDto objeto, String orden) throws ProductoNotFoundException {
        DetalleOrden nuevo = mapper.detalleInToDetalle(objeto);
        Producto producto = productoRepository.findById(objeto.getId_producto()).orElseThrow(()
                -> new ProductoNotFoundException("Produto no encontrado"));
        //Validacion de la entrada de cantidad y vacio de cantidad en el producto
        if (producto.getCantidad() - objeto.getCantidad() == 0) {
            producto.setCantidad(0);
            producto.setEnable(false);
            productoRepository.save(producto);
        } else if (producto.getCantidad() - objeto.getCantidad() < 0 || objeto.getCantidad() < 0) {
            throw new ProductoNotFoundException("Cantidad erronea");
        } else {
            producto.setCantidad(producto.getCantidad() - objeto.getCantidad());
            productoRepository.save(producto);
        }
        nuevo.setProducto(productoRepository.getReferenceById(objeto.getId_producto()));
        nuevo.setOrden(ordenRepository.getReferenceById(orden));
        repository.save(nuevo);
        return objeto;
    }

    @Override
    public DetalleOrdenOutDto findOne(String id) throws OrdenNotFoundException {
        if (!repository.existsById(id)) {
            throw new OrdenNotFoundException("Detalle not found");
        } else {
            DetalleOrden orden = repository.findById(id).orElseThrow(()
                    -> new OrdenNotFoundException("Detalle no encontrado"));
            return mapper.detalleToDetalleOutDto(orden);
        }

    }

    @Override
    public List<DetalleOrdenOutDto> getAllByOrden(String num) throws OrdenNotFoundException {
        Orden orden = ordenRepository.findById(num).orElseThrow(()
                -> new OrdenNotFoundException("Orden no encontrada"));
        return repository.findAllByOrden_id(orden.getId())
                .stream().map(mapper::detalleToDetalleOutDto).collect(Collectors.toList());
    }

}
