package com.idos.apk.backend.tienda.tatuajes.model.mapper.producto;

import com.idos.apk.backend.tienda.tatuajes.model.Producto;
import com.idos.apk.backend.tienda.tatuajes.model.dto.producto.ProductoDTOOut;
import com.idos.apk.backend.tienda.tatuajes.model.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class ProductoToProductoDTOOut implements Mapper<Producto, ProductoDTOOut> {
    @Override
    public ProductoDTOOut map(Producto in) {
        ProductoDTOOut p = new ProductoDTOOut(in.getNombre(), in.getDescripcion(), convertImageToBase64(in.getImg()), in.getPrecio(), in.isEnable(), in.getCantidad(), in.getId());
        return p;
    }
    public static String convertImageToBase64(String imagePath){
       try{ Path path = Paths.get(imagePath);
        byte[] imageBytes = Files.readAllBytes(path);
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return base64Image;}catch (IOException ex){
           return "Imagen no valida";
       }
    }
}
