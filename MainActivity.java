package com.example.edgarvaldivia.iniciosesion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int REQUEST_SIGNUP = 0;

    /*Mis Elementos*/
    Button botoniniciar;
    EditText usuario;
    EditText contraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario =  (EditText) findViewById(R.id.tilUsuario);
        contraseña =  (EditText) findViewById(R.id.tilContraseña);
        botoniniciar = (Button) findViewById(R.id.btnInicioSesion);


        botoniniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciousuario();
            }
        });}





    public void iniciousuario() {
        Log.d(TAG, "Inició");

        if (!metodovalidar()) {
            metodoFallologin();
            return;
        }

        botoniniciar.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Espere un momento...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        metodoInicioExitoso();
                        progressDialog.dismiss();
                    }
                }, 6000);
    }//iniciousuario





    /*Etiquetas y Validaciones*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }

    public void metodoInicioExitoso() {
        botoniniciar.setEnabled(true);
        finish();
    }

    public void metodoFallologin() {
        Toast.makeText(getBaseContext(), "Inicio de Sesión Inválido", Toast.LENGTH_LONG).show();
        botoniniciar.setEnabled(true);
    }

    public boolean metodovalidar() {
        boolean valid = true;

        String cadusuario = usuario.getText().toString();
        String cadcontraseña = contraseña.getText().toString();

        if (cadusuario.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(cadusuario).matches()) {
            usuario.setError("Usuario Inválido");
            valid = false;
        } else {
            usuario.setError(null);
        }

        if (cadcontraseña.isEmpty() || cadcontraseña.length() < 4 || cadcontraseña.length() > 10) {
            contraseña.setError("QUE SEA LA CONTRASEÑA SEA MAYOR DE 4 Y MENOR DE 10 WE, O SE LO CAMBIAS NOSE");
            valid = false;
        } else {
            contraseña.setError(null);
        }

        return valid;
    }//validar
}//class
