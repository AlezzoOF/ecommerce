package com.idos.apk.backend.tienda.tatuajes.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {
    private String message;
    private HttpStatus status;
    private Date timestamp;
}
