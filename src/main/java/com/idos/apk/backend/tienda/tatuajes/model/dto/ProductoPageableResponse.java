package com.idos.apk.backend.tienda.tatuajes.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductoPageableResponse {
    private List<ProductoDTOOut> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
