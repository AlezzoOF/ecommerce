package com.idos.apk.backend.tienda.tatuajes.mapper;

import com.idos.apk.backend.tienda.tatuajes.dto.user.RegisterDto;
import com.idos.apk.backend.tienda.tatuajes.dto.user.UserOutDto;
import com.idos.apk.backend.tienda.tatuajes.model.Usuario;
import org.apache.catalina.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Usuario registerDtoToUser(RegisterDto registerDto);

    UserOutDto userToUserOut(Usuario usuario);
}
