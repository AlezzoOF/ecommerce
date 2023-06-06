package com.idos.apk.backend.tienda.tatuajes.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class RequestException extends RuntimeException{
    private HttpStatus status;

    public RequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
