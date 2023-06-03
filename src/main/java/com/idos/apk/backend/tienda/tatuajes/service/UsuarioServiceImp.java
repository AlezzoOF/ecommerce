package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.UserDtoOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.user.RegisterDtoInToUser;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.user.UserInToDtoOut;
import com.idos.apk.backend.tienda.tatuajes.repository.UsuarioRepository;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository repository;
    private final UserInToDtoOut mapper2;

    private final RegisterDtoInToUser mapper;

    public UsuarioServiceImp(UsuarioRepository repository, UserInToDtoOut mapper2, RegisterDtoInToUser mapper) {
        this.repository = repository;
        this.mapper2 = mapper2;
        this.mapper = mapper;
    }


    @Override
    @Transactional
    public void saveUser(RegisterDto registerDto) {
        if (repository.existsByEmail(registerDto.userName())) {
            throw new UsernameNotFoundException("User already exist");
        }
        Usuario user = mapper.map(registerDto);
        user.setRol("USER");
        repository.save(user);
//        System.out.println(user.getNombre());
    }

    @Override
    @Transactional
    public void saveUserLikeAdmin(RegisterDto registerDto) {
        if (repository.existsByEmail(registerDto.userName())) {
            throw new UsernameNotFoundException("User already exist");
        }
        Usuario user = mapper.map(registerDto);
        user.setRol("ADMIN");
        repository.save(user);
    }

    @Override
    public Usuario valid(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public UserDtoOut findByEmail(String email) {
        if (repository.existsByEmail(email)) {
            return mapper2.map(repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found")));
        } else {
            return null;
        }
    }


    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
