package com.example.mispeliculas;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Peticiones
{
    @GET("ListaArticulos")
    Call<List<Producto>> getProductos();
}
