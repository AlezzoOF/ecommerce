package com.idos.apk.backend.tienda.tatuajes.model.dto.user;

import com.idos.apk.backend.tienda.tatuajes.model.Rol;

import java.util.List;

public record AuthResponse(String token, String tokenType, List<String> rol) {
}
