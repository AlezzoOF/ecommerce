package com.idos.apk.backend.tienda.tatuajes.dto.producto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoInDto {
    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Size(max = 100, message = "Exedio el tamano max")
    private String nombre;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Size(max = 200, message = "Exedio el tamano max")
    private String descripcion;

    @Min(0)
    private Double precio;

    @Min(0)
    private Integer cantidad;

    @NotNull(message = "No puede ser nulo")
    @NotEmpty(message = "No puede ser vacio")
    @Size(max = 50, message = "Exedio el tamano max")
    private String tipo;
}
