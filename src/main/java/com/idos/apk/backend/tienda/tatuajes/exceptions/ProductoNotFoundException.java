package com.idos.apk.backend.tienda.tatuajes.exceptions;

import java.io.Serial;

public class ProductoNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;

    public ProductoNotFoundException(String message) {
        super(message);
    }
}
