package com.idos.apk.backend.tienda.tatuajes.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    void init() throws IOException;

    String store(MultipartFile file);

    void deleteFile(String filename);
}
