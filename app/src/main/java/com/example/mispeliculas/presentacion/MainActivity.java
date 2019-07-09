package com.example.mispeliculas.presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mispeliculas.dominio.IUsuarioLogic;
import com.example.mispeliculas.dominio.UsuarioLogic;
import com.example.mispeliculas.dto.UsuarioNuevoDto;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import com.example.mispeliculas.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
{
    //region Declaración Variables
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;

    private RadioButton radioButtonNuevo;
    private RadioButton radioButtonCliente;

    private FirebaseAuth firebaseAuth;

    private CallbackManager mCallbackManager;

    private GoogleSignInClient googleSignInClient;
    private GoogleSignInOptions googleSignInOptions;

    private View contenedornuevo;
    private View contenedorcliente;

    private EditText NombreUsuarioCliente;
    private EditText ContraseñaUsuarioCliente;

    private EditText NombreUsuarioNuevo;
    private EditText MailUsuarioNuevo;
    private EditText ContraseñaUsuarioNuevo;

    private int GOOGLE_SIGN_IN = 7;
    private int FACEBOOK_SIGN_IN = 64206;

    //endregion

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.main_activity);

        firebaseAuth= FirebaseAuth.getInstance();

        // region Asignacion Memoria
        radioButtonNuevo = findViewById(R.id.radioNuevo);
        radioButtonCliente = findViewById(R.id.radioCliente);

        contenedornuevo = findViewById(R.id.ll_contenedor_nuevo);
        contenedorcliente = findViewById(R.id.ll_contenedor_cliente);

        NombreUsuarioCliente= findViewById(R.id.txtMailCliente);
        ContraseñaUsuarioCliente  = findViewById(R.id.txtContraseñaCliente);

        NombreUsuarioNuevo= findViewById(R.id.txtNombre);
        MailUsuarioNuevo= findViewById(R.id.txtMail);
        ContraseñaUsuarioNuevo= findViewById(R.id.txtContraseña);

        //endregion
    }

    public void btnIngresarFacebook(View view)
    {
        mCallbackManager = CallbackManager.Factory.create();

        LoginButton loginButtonFacebook = findViewById(R.id.buttonFacebook);

        loginButtonFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                Log.i("INFO", "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel()
            {
                Log.i("INFO", "facebook:onCancel");
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }

            @Override
            public void onError(FacebookException error)
            {
                Log.e("ERROR", "facebook:onError", error);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        });
    }

    public void btnIngresarGmail(View view)
    {
        SignInButton ButtonGmail = findViewById(R.id.buttonGmail);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        googleSignInClient  = GoogleSignIn.getClient(this, googleSignInOptions);

        ButtonGmail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                signIn();
            }
        });
    }

    //Metodo para cuando seleccionan las opciones de ingreso de los radiobutton
    public void ClicRadioButton(View view)
    {
            boolean marcado = ((RadioButton) view).isChecked();

            switch (view.getId())
            {
                case R.id.radioNuevo:
                    if (marcado)
                    {
                       // radioButtonCliente.setVisibility(View.GONE);
                        MostrarContenedor(true);
                    }
                    break;

                case R.id.radioCliente:
                    if (marcado)
                    {
                        MostrarContenedor(false);
                    }
                    break;
            }
    }

    //Método que indica que opcion mostrar si es cliente nuevo o no
    private void MostrarContenedor(boolean b)
    {
      contenedornuevo.setVisibility(b ? View.VISIBLE : View.GONE);

      contenedorcliente.setVisibility(b ? View.GONE : View.VISIBLE);
    }

    //Método que botón para seguir usuario que ya es cliente
    public void btnSeguiVe(View view)
    {
        try{
            firebaseAuth.signInWithEmailAndPassword(NombreUsuarioCliente.getText()+"",ContraseñaUsuarioCliente.getText()+"").
                    addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                //Log para auditoria
                               /* Snackbar.make(view, "Sesion iniciada correctamente", Snackbar.LENGTH_LONG).show();
                                */
                                Log.i("INFO", "Sesion iniciada correctamente");

                                //Log en pantalla
                                Toast toast1 = Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT);
                                toast1.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);
                                toast1.show();

                                IniciarCarrito();

                            }else{
                                // log para auditoria
                                Log.e("ERROR",task.getException().getMessage());

                                //Log en pantalla
                                Toast toast1 = Toast.makeText(getApplicationContext(), "O no es tú usuario o tú contraseña oís", Toast.LENGTH_SHORT);
                                toast1.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);
                                toast1.show();

                               /* Snackbar.make(view, "No ha sido posible iniciar sesión " + task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
                                Log.e("ERROR", "No ha sido posible iniciar sesión " + task.getException().getMessage());*/
                            }
                        }
                    });

        }catch (Exception exception){
            Log.e("ERROR", exception.getMessage());
        }
    }

    public void btnCrearUsuario(View view)
    {
        try
        {
            //llamado a la logica
            IUsuarioLogic iUsuarioLogic = new UsuarioLogic();

            iUsuarioLogic.crearUsuarioNuevo(NombreUsuarioNuevo.getText()+"",MailUsuarioNuevo.getText()+"",ContraseñaUsuarioNuevo.getText()+"");

            Toast.makeText(MainActivity.this, "Ya sos parte de la familias, oís!!!.", Toast.LENGTH_SHORT).show();

            IniciarCarrito();

        }catch (Exception exception)
        {
            Log.e("ERROR", exception.getMessage());
        }
    }

    //Método que llama la activity que contiene el proceso del carrito
    protected void IniciarCarrito()
    {
        Intent intent = new Intent(MainActivity.this, IngresoClienteActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

                //Log en pantalla
                Toast toast1 = Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);
                toast1.show();

                IniciarCarrito();

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.e("ERROR", "Google sign in failed", e);
                // ...
            }
        }
        if(requestCode == FACEBOOK_SIGN_IN){
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct)
    {
        Log.i("INFO", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("INFO", "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("ERROR", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token)
    {
        Log.i("INFO", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("INFO", "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("INFO", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user)
    {
        if (user != null) {
            String sbUser = user.getDisplayName();
            Toast.makeText(MainActivity.this, "Hola " + sbUser,Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "No ha sido posible iniciar la sesion",Toast.LENGTH_LONG).show();
        }
    }

    private void signIn()
    {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    public void signOut()
    {
        firebaseAuth.signOut();
        LoginManager.getInstance().logOut();

        updateUI(null);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        updateUI(null);
    }

    public void btnTemporal(View view)
    {
        IniciarCarrito();
    }

}

