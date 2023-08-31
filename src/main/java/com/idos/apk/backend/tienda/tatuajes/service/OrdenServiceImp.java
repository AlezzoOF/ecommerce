package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenAgnoDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenInDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenMesDto;
import com.idos.apk.backend.tienda.tatuajes.dto.orden.OrdenOutDto;
import com.idos.apk.backend.tienda.tatuajes.exceptions.OrdenNotFoundException;
import com.idos.apk.backend.tienda.tatuajes.mapper.OrdenMapper;
import com.idos.apk.backend.tienda.tatuajes.model.Orden;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.repository.OrdenRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.ProductoRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.UsuarioRepository;
import com.idos.apk.backend.tienda.tatuajes.security.JWTGenerator;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.DetalleOrdenService;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.OrdenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OrdenServiceImp implements OrdenService {

    private final OrdenRepository repository;
    private final DetalleOrdenService serviceDetalle;
    private final OrdenMapper mapper;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final JWTGenerator generator;




    @Override
    @Transactional
    public OrdenOutDto save(OrdenInDto objeto) throws UsernameNotFoundException {
        String email = generator.getUsernameFromJwt(objeto.getToken());
        Usuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no válido"));

        Orden nueva = mapper.ordenInToOrden(objeto);
        nueva.setUsuario(user);
        Orden guardada = repository.save(nueva);
        return mapper.ordenToOrdenOut(guardada);
    }

    @Override
    public List<OrdenOutDto> getAllByUser(String token) throws UsernameNotFoundException {
        String email = generator.getUsernameFromJwt(token);
        Usuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return repository.findAllByUsuario_id(user.getId()).stream()
                .map(mapper::ordenToOrdenOut)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdenOutDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::ordenToOrdenOut)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdenOutDto> getAllByDate(int mes, String agno) {
        return repository.findAllByMesAndAgno(mes, agno).stream()
                .map(mapper::ordenToOrdenOut)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdenOutDto> getAllByUserAdmin(String id) {
        return repository.findAllByUsuario_id(id).stream()
                .map(mapper::ordenToOrdenOut)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws OrdenNotFoundException {
        if (!repository.existsById(id)){
            throw new OrdenNotFoundException("Orden no existente");
        }
        repository.deleteById(id);
    }

    @Override
    public OrdenAgnoDto filtroMes(String agno) {
        List<OrdenMesDto> lista2 = IntStream.rangeClosed(1, 12)
                .mapToObj(i -> {
                    List<Orden> lista = repository.findAllByMesAndAgno(i, agno);
                    double totalMes = lista.stream()
                            .mapToDouble(Orden::getTotal)
                            .sum();
                    double porciento = getPorciento(lista.size());
                    return OrdenMesDto.builder()
                            .cantOrdenes(lista.size())
                            .totalMes(totalMes)
                            .mes(i)
                            .porciento(porciento)
                            .build();
                })
                .collect(Collectors.toList());

        int totalOrd = (int) repository.count();
        double totalDinero = repository.findAll().stream()
                .mapToDouble(Orden::getTotal)
                .sum();

        return OrdenAgnoDto.builder()
                .totalOrd(totalOrd)
                .totalDinero(totalDinero)
                .lista(lista2)
                .build();
    }

    private double getPorciento(int size) {
        int totalOrd = (int) repository.count();
        return (double) size * 100 / totalOrd;
    }
}
