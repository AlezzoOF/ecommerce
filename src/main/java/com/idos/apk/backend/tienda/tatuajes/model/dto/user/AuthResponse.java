package com.idos.apk.backend.tienda.tatuajes.model.dto.user;

import java.util.List;

public record AuthResponse(String token, String tokenType, List<String> rol) {
}
