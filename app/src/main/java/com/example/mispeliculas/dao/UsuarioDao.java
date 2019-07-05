package com.example.mispeliculas.dao;

import com.example.mispeliculas.dto.UsuarioNuevoDto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsuarioDao implements IUsuarioDao
{
    //obtener instancia de la bd
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    //crear referencia a la bd
    DatabaseReference databaseReference = firebaseDatabase.getReference("Usuarios");

    @Override
    public void crearUsuario(UsuarioNuevoDto usuarioNuevoDto) throws Exception
    {
        try
        {   //crear nuevo nodo
            databaseReference.child(usuarioNuevoDto.getUsername()+"").setValue(usuarioNuevoDto);

        }catch(Exception exception)
        {
            throw new Exception("Error creando usuario:"+exception.getMessage());
        }
    }
}
