package com.idos.apk.backend.tienda.tatuajes.repository;

import com.idos.apk.backend.tienda.tatuajes.model.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepo extends JpaRepository<BlacklistedToken, String> {
    boolean existsByToken(String id);
}
