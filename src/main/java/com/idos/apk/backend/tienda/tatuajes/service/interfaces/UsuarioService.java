package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.RegisterDto;

import java.util.Optional;

public interface UsuarioService {
    void saveUser(RegisterDto registerDto);
    void saveUserLikeAdmin(RegisterDto registerDto);


    Optional<Usuario> findByEmail(String email);



    Boolean existsByEmail(String email);
}
