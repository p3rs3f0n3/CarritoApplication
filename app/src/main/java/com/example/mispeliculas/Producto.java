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
    private Integer inventario;

    @SerializedName("Precio")
    @Expose
    private Double precio;

    @SerializedName("imagen")
    @Expose
    private String imagen;

    @SerializedName("categoria")
    @Expose
    private String categoria;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }


    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }


    public Integer getInventario()
    {
        return inventario;
    }

    public void setInventario(Integer inventario)
    {
        this.inventario = inventario;
    }


    public Double getPrecio()
    {
        return precio;
    }

    public void setPrecio(Double precio)
    {
        this.precio = precio;
    }


    public String getImagen()
    {
        return imagen;
    }

    public void setImagen(String imagen)
    {
        this.imagen = imagen;
    }


    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }
}
