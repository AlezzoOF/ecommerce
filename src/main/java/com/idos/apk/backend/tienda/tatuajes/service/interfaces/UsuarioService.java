package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.Rol;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.service.UsuarioServiceImp;

import java.util.Optional;

public interface UsuarioService {
    void saveUser(RegisterDto registerDto);
    Optional<Usuario> findByEmail(String email);
    Optional<Rol> findRolByName(String name);
    Boolean existsByEmail(String email);
}
