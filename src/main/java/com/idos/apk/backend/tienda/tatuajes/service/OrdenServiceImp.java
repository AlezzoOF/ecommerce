package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.exceptions.OrdenNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.orden.OrdenDtoInInToOrden;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.orden.OrdenInToOrdenDto;
import com.idos.apk.backend.tienda.tatuajes.repository.OrdenRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.ProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.UsuarioRepository;
import com.idos.apk.backend.tienda.tatuajes.security.JWTGenerator;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.OrdenService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenServiceImp implements OrdenService {

    private final OrdenRepository repository;
    private final DetalleOrdenService serviceDetalle;
    private final OrdenInToOrdenDto mapper;
    private final OrdenDtoInInToOrden mapper2;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final JWTGenerator generator;

    public OrdenServiceImp(OrdenRepository repository, DetalleOrdenService serviceDetalle, OrdenInToOrdenDto mapper, OrdenDtoInInToOrden mapper2, ProductoRepository productoRepository, UsuarioRepository usuarioRepository, JWTGenerator generator) {
        this.repository = repository;
        this.serviceDetalle = serviceDetalle;
        this.mapper = mapper;
        this.mapper2 = mapper2;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.generator = generator;
    }


    @Override
    @Transactional
    public OrdenDtoOut save(OrdenDtoIn objeto) {
        Orden nueva = mapper2.map(objeto);
        String email = generator.getUsernameFromJwt(objeto.token());
        nueva.setUsuario(usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no valido")));//setear usuario
        Orden guardada = repository.save(nueva);
        return mapper.map(guardada);
    }

    @Override
    public List<OrdenDtoOut> getAllByUser(String token) {
        String email = generator.getUsernameFromJwt(token);
        Usuario user = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Orden> lista = repository.findAllByUsuario_id(user.getId());
        List<OrdenDtoOut> enviar = lista.stream().map(p -> mapper.map(p)).collect(Collectors.toList());
        return enviar;
    }

    @Override
    public void delete(String id) {
        Orden nueva = repository.findById(id).orElseThrow(() -> new OrdenNotFoundException("Orden no encontrada"));
        repository.deleteById(id);
    }
}
