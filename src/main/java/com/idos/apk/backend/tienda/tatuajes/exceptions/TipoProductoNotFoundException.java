package com.idos.apk.backend.tienda.tatuajes.exceptions;

public class TipoProductoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public TipoProductoNotFoundException(String message) {
        super(message);
    }
}
