package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.UserDtoOut;

import java.util.List;

public interface UsuarioService {
    void saveUser(RegisterDto registerDto);

    void saveUserLikeAdmin(RegisterDto registerDto);

    Usuario valid(String email);

    UserDtoOut findByEmail(String email);


    Boolean existsByEmail(String email);

    List<UserDtoOut> getAll();

    UserDtoOut editRol(String id, String rol);
}
