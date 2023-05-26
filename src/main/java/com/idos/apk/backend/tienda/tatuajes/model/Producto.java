package com.idos.apk.backend.tienda.tatuajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String descripcion;
    private String img;
    private double precio;
    private int cantidad;
    @ManyToOne
    @JoinColumn(name = "tipoProductoId")
    private TipoProducto tipo;
    private boolean enable = true;


    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }




    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", img='" + img + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", tipo=" + tipo +
                ", enable=" + enable +
                '}';
    }
}
