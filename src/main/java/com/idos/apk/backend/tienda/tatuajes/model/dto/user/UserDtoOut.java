package com.idos.apk.backend.tienda.tatuajes.model.dto.user;

public record UserDtoOut(
        String id,
        String userName,
        String nombre,

        String apellido,

        String direccion,
        String rol) {
}
