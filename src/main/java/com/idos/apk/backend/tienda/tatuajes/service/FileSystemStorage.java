package com.idos.apk.backend.tienda.tatuajes.service;

import com.idos.apk.backend.tienda.tatuajes.service.interfaces.StorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileSystemStorage implements StorageService {
    @Value("${media.location}")
    private String mediaLocation;
    private Path rootLocation;

    @PostConstruct
    @Override
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        if (!Files.exists(rootLocation)){
        Files.createDirectory(rootLocation);}

    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()){
                throw new RuntimeException("File is null");
            }
            String fileName = UUID.randomUUID().toString() + ".jpg";
            Path destinationFile = rootLocation.resolve(Paths.get(fileName))
                    .normalize().toAbsolutePath();

            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

            }
            return fileName;
        }catch (IOException ex){
            throw new RuntimeException("Fallo el guardado");
        }

    }

    @Override
    public Resource loadResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()){
                return resource;
            }else {
                throw new RuntimeException("No se encotro el archivo");
            }
        }catch (IOException ex){
            throw new RuntimeException("No se encontro el archivo");
        }

    }
}
