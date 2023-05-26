package com.idos.apk.backend.tienda.tatuajes.exceptions;

public class TipoProductoAllReadyExist extends RuntimeException{
    private static final long serialVersionUID = 1;

    public TipoProductoAllReadyExist(String message) {
        super(message);
    }
}
