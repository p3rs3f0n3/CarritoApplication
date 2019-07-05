package com.example.mispeliculas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Producto
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("Inventario")
    @Expose
    private Object inventario;
    @SerializedName("Precio")
    @Expose
    private Object precio;
    @SerializedName("categoria")
    @Expose
    private String categoria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getInventario() {
        return inventario;
    }

    public void setInventario(Object inventario) {
        this.inventario = inventario;
    }

    public Object getPrecio() {
        return precio;
    }

    public void setPrecio(Object precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
