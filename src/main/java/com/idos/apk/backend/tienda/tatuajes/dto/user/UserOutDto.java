package com.idos.apk.backend.tienda.tatuajes.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOutDto {
    private String id;
    private String userName;
    private String nombre;
    private String apellido;
    private String direccion;
    private String rol;
}
