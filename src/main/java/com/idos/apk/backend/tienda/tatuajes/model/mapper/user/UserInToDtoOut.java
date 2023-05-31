package com.idos.apk.backend.tienda.tatuajes.model.mapper.user;

import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import com.idos.apk.backend.tienda.tatuajes.model.dto.user.UserDtoOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserInToDtoOut implements Mapper<Usuario, UserDtoOut> {
    @Override
    public UserDtoOut map(Usuario in) {
        UserDtoOut user = new UserDtoOut(in.getEmail(), in.getNombre(), in.getApellido(),in.getDireccion());
        return user;
    }
}
