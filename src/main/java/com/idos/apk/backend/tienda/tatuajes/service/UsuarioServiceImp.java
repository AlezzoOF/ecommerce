package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.model.Rol;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.user.RegisterDtoInToUser;
import com.idos.apk.backend.tienda.tatuajes.repository.RolRepository;
import com.idos.apk.backend.tienda.tatuajes.repository.UsuarioRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository repository;
    private final RolRepository rolRepository;

    private final RegisterDtoInToUser mapper;

    public UsuarioServiceImp(UsuarioRepository repository, RolRepository rolRepository, RegisterDtoInToUser mapper) {
        this.repository = repository;
        this.rolRepository = rolRepository;
        this.mapper = mapper;
    }


    @Override
    public void saveUser(RegisterDto registerDto) {
        Usuario user = mapper.map(registerDto);
        Rol rol = rolRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(rol));
        repository.save(user);
//        System.out.println(user.getNombre());


    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Rol> findRolByName(String name) {
        return rolRepository.findByName(name);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
