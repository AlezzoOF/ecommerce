package com.idos.apk.backend.tienda.tatuajes.exceptions;

public class OrdenNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public OrdenNotFoundException(String message) {
        super(message);
    }
}

