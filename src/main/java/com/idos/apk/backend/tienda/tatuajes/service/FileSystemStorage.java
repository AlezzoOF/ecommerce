package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.service.interfaces.StorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
public class FileSystemStorage implements StorageService {
    @Value("${media.location}")
    private String mediaLocation;
    private Path rootLocation;

    @PostConstruct
    @Override
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        if (!Files.exists(rootLocation)) {
            Files.createDirectory(rootLocation);
        }

    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is null");
            }
            String fileName = UUID.randomUUID().toString() + ".jpg";
            Path destinationFile = rootLocation.resolve(Paths.get(fileName))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

            }
            return mediaLocation + "/" + fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Fallo el guardado");
        }

    }

    @Override
    public void loadResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Files.delete(file);

        } catch (IOException exe) {
            throw new RuntimeException("No se encontro el archivo");
        }

    }
}
