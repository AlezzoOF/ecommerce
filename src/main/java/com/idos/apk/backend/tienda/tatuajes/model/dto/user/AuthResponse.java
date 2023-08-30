package com.idos.apk.backend.tienda.tatuajes.model.dto.user;

public record AuthResponse(String token, String tokenType, String rol, String username, String name, String last_name) {
}
