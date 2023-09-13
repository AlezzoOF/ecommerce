package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.model.BlacklistedToken;
import com.idos.apk.backend.tienda.tatuajes.repository.BlackListRepo;
import com.idos.apk.backend.tienda.tatuajes.security.JWTGenerator;
import com.idos.apk.backend.tienda.tatuajes.security.SecurityConstants;
import com.idos.apk.backend.tienda.tatuajes.service.interfaces.IBlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BlackListService implements IBlackListService {
    private final BlackListRepo repo;

    @Override
    public BlacklistedToken save(BlacklistedToken token){
        return repo.save(token);
    }

    @Override
    public boolean exist(String token) {
        return repo.existsByToken(token);

    }

    @Override
    @Scheduled(fixedRate = 4 * 60 * 60 * 1000) // Ejecutar cada 4 horas (en milisegundos)
    public void deleteExpiredToken() {
        Date currentDate = new Date();
        List<BlacklistedToken> tokens = repo.findAll();

        for (BlacklistedToken token: tokens){
            if (currentDate.compareTo(token.getExpirationDate()) >= 0){
                repo.deleteById(token.getId());
            }
        }
    }
}
