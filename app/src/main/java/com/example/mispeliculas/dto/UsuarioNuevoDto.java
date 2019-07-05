package com.example.mispeliculas.dto;

public class UsuarioNuevoDto
{
    public String username;
    public String email;
    public String contraseña;

    public UsuarioNuevoDto()
    {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UsuarioNuevoDto(String username, String email, String contraseña)
    {
        this.username = username;
        this.email = email;
        this.contraseña = contraseña;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
