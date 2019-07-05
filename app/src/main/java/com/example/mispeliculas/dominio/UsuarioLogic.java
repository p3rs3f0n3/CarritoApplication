package com.example.mispeliculas.dominio;

import android.view.Gravity;
import android.widget.Toast;

import com.example.mispeliculas.dao.IUsuarioDao;
import com.example.mispeliculas.dao.UsuarioDao;
import com.example.mispeliculas.dto.UsuarioNuevoDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioLogic implements IUsuarioLogic
{
    @Override
    public void crearUsuarioNuevo(String username, String email, String contraseña) throws Exception
    {
        if(username.length()<8)
        {
            throw new Exception(("el usuario tener entre 8 y 10 dígitos"));
        }
        else if(username.length()>=11)
        {
            throw new Exception(("el usuario tener entre 8 y 10 dígitos"));
        }

        if(!isEmailValid(email))
        {
             throw new Exception(("el correo digitado no es un valido "));
        }

        if(contraseña.length()<8)
        {
            throw new Exception(("la contraseña debe tener más de 8 digitos"));
        }

        //crear instancia del dao por medio de la interfaz
        IUsuarioDao iUsuarioDao = new UsuarioDao();
        iUsuarioDao.crearUsuario(new UsuarioNuevoDto(username, email, contraseña));
    }

    public static boolean isEmailValid(String email)
    {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
        {
            isValid = true;
        }

        return isValid;
    }
}
