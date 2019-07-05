package com.example.mispeliculas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>
{
    private List<Producto> listProductos;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public MyRecyclerAdapter(List<Producto> lstProduct, Context context)
    {
        mInflater= LayoutInflater.from(context);
        listProductos= lstProduct;
    }


    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        View view = mInflater.inflate(R.layout.recyclerview_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder myViewHolder, int i) {
        String nombre = listProductos.get(i).getNombre();
        myViewHolder.myTextView.setText(nombre);
    }

    @Override
    public int getItemCount() {
        int retorno= (listProductos.size()>0)? listProductos.size():0;
        return retorno;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.txtName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return listProductos.get(id).getNombre();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
