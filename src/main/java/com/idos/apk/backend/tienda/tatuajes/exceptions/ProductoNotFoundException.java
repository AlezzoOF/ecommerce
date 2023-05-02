package com.idos.apk.backend.tienda.tatuajes.exceptions;

public class ProductoNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public ProductoNotFoundException(String message){
        super(message);
    }
}
