package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.exceptions.OrdenNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoIn;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenDtoOut;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenPorAgno;
import com.idos.apk.backend.tienda.tatuajes.model.dto.orden.OrdenPorMes;
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

import java.util.ArrayList;
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
    public OrdenDtoOut save(OrdenDtoIn objeto) throws UsernameNotFoundException {
        Orden nueva = mapper2.map(objeto);
        String email = generator.getUsernameFromJwt(objeto.token());
        nueva.setUsuario(usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no valido")));//setear usuario
        Orden guardada = repository.save(nueva);
        return mapper.map(guardada);
    }

    @Override
    public List<OrdenDtoOut> getAllByUser(String token) throws UsernameNotFoundException {
        String email = generator.getUsernameFromJwt(token);
        Usuario user = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Orden> lista = repository.findAllByUsuario_id(user.getId());
        return lista.stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws OrdenNotFoundException {
        if (!repository.existsById(id)){
            throw new OrdenNotFoundException("Orden no existente");
        }
        repository.deleteById(id);
    }

    @Override
    public OrdenPorAgno filtroMes(String agno) {
        List<OrdenPorMes>lista2 = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            List<Orden>lista = repository.findAllByMesAndAgno(i, agno);
            OrdenPorMes ordenPorMes = OrdenPorMes.builder()
                    .cantOrdenes(lista.size())
                    .totalMes(lista.stream()
                            .mapToDouble(Orden::getTotal)
                            .sum())
                    .mes(i)
                    .porciento(getPorciento(lista.size()))
                    .build();
            lista2.add(ordenPorMes);
        }

        return OrdenPorAgno.builder()
                .totalOrd(repository.findAll().size())
                .totalDinero(repository.findAll().stream()
                        .mapToDouble(Orden::getTotal)
                        .sum())
                .lista(lista2)
                .build();
    }

    private double getPorciento(int size) {
        return (double) size*100/repository.findAll().size();
    }
}
