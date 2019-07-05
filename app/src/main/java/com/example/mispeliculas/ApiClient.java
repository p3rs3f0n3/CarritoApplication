package com.example.mispeliculas;

import android.content.Context;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    public final static String BASE_URL= "https://appcarritocompras.herokuapp.com/api/";
    private Context context;
    private Retrofit retrofit;

    public ApiClient(Context context)
    {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public Call<List<Producto>> getProductos()
    {
        Peticiones service = retrofit.create(Peticiones.class);
        return service.getProductos();
    }

}
