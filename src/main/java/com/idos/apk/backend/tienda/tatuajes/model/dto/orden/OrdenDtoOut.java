package com.idos.apk.backend.tienda.tatuajes.model.dto.orden;



import java.time.LocalDate;
import java.util.Date;


public record OrdenDtoOut(double total, String numero, LocalDate fechaCreacion) {
}
