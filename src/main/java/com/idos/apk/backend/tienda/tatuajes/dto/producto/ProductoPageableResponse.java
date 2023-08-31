package com.idos.apk.backend.tienda.tatuajes.dto.producto;

import com.idos.apk.backend.tienda.tatuajes.dto.producto.ProductoOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoPageableResponse {
    private List<ProductoOutDto> content;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean last;
}
