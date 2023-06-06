package com.idos.apk.backend.tienda.tatuajes.exceptions;

public class ServerError extends RuntimeException{
    private static final long serialVersionUID = 1;

    public ServerError(String message) {
        super(message);
    }
}
