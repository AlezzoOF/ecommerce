package com.idos.apk.backend.tienda.tatuajes.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String tokenType;
    private String rol;
    private String name;
    private String username;
    private String last_name;
}
