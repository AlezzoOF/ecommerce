package com.idos.apk.backend.tienda.tatuajes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_ordenes")
public class DetalleOrden {
    @Id
    @GeneratedValue
    private Long id;
    private int cantidad;
    private double total;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_id")
    private Orden orden;
    @OneToOne
    private Producto producto;

    public DetalleOrden() {
    }

    public DetalleOrden(Long id, int cantidad, double total) {
        this.id = id;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "DetalleOrden{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", total=" + total +
                '}';
    }
}
