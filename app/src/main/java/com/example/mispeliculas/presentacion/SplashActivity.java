package com.example.mispeliculas.presentacion;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mispeliculas.R;

public class SplashActivity extends AppCompatActivity
{
    private final int DURACION_SPLASH = 3000;
    
    @Override
    protected void onCreate(Bundle saveInstance)
    {
        //metodo que hace vibrar la app al iniciar
        //inicioAplicacion();

        super.onCreate(saveInstance);
        setContentView(R.layout.splash);
        
        new Handler().postDelayed(new Runnable()
        {
            public void run ()
            {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        },DURACION_SPLASH);
    }

    public void inicioAplicacion()
    {
        try
        {
            Vibrator vibrator=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            vibrator.vibrate(VibrationEffect.createOneShot(1000,VibrationEffect.DEFAULT_AMPLITUDE));

        }
        catch (Exception exception)
        {
          Log.e("ERROR", exception.getMessage());
        }
    }

}


