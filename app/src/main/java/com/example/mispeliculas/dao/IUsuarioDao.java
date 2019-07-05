package com.example.mispeliculas.dao;

import com.example.mispeliculas.dto.UsuarioNuevoDto;

public interface IUsuarioDao
{
    public void crearUsuario(UsuarioNuevoDto usuarioNuevoDto) throws  Exception;
}
