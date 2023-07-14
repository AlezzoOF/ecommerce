package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import com.idos.apk.backend.tienda.tatuajes.model.BlacklistedToken;

public interface IBlackListService {
    BlacklistedToken save(String token);

    boolean exist(String token);

    void deleteExpiredToken();
}
