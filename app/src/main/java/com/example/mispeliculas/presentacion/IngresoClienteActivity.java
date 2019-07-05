package com.example.mispeliculas.presentacion;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mispeliculas.ApiClient;
import com.example.mispeliculas.MyRecyclerAdapter;
import com.example.mispeliculas.Producto;
import com.example.mispeliculas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngresoClienteActivity extends AppCompatActivity
{
    private ListView listView;
    private List<Producto> lProductos;
    private ArrayAdapter<String> adaptador;

    private RecyclerView recyclerProductos;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresocliente_activity);

        cargarProductos();
    }

    private void cargarProductos()
    {
        ApiClient apiClient = new ApiClient(this);

        final Call<List<Producto>> productos =apiClient.getProductos();

        productos.enqueue(new Callback<List<Producto>>()
        {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response)
            {
                Log.i("INFO","SE OBTUVO INFORMACIÓN");

                lProductos = response.body();

                fillInterface(lProductos);
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t)
            {
                Log.e("ERROR","SE GENERÓ UN ERROR ");
            }
        });
    }

    void fillInterface(List<Producto> lstProductosN )
    {
        recyclerProductos = (RecyclerView)findViewById(R.id.RecyclerProductos);
        recyclerProductos.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerProductos.setLayoutManager(layoutManager);
        mAdapter = new MyRecyclerAdapter(lProductos, this);
        recyclerProductos.setAdapter(mAdapter);
    }
}
