package com.idos.apk.backend.tienda.tatuajes.model.dto.orden;


import java.time.LocalDate;


public record OrdenDtoOut(double total, String numero, LocalDate fechaCreacion, String id) {
}
