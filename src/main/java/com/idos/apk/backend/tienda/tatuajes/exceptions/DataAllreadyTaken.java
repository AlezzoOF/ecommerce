package com.idos.apk.backend.tienda.tatuajes.exceptions;

public class DataAllreadyTaken extends RuntimeException {
    public DataAllreadyTaken(String message) {
        super(message);
    }
}
