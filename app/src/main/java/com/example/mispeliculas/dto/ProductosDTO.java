package com.example.mispeliculas.dto;

public class ProductosDTO
{
    private String id;
    private String nombre;
    private String Inventario;
    private String Precio;
    private String categoria;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInventario() {
        return Inventario;
    }

    public void setInventario(String inventario) {
        Inventario = inventario;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
