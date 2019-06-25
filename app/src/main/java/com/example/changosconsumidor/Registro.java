package com.example.changosconsumidor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        final EditText nombreUsuario = (EditText) findViewById(R.id.etNuevoNombre);
        final EditText contraseña = (EditText) findViewById(R.id.etNuevaContra);
        final EditText email = (EditText) findViewById(R.id.etNuevoEmail);
        final Button btnRegistro = (Button) findViewById(R.id.botonNuevoRegistro);


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferencias = getSharedPreferences("Mis Preferencias", MODE_PRIVATE);
                String nuevoUsuario = nombreUsuario.getText().toString();
                String nuevoContra = contraseña.getText().toString();
                String nuevoEmail = email.getText().toString();

                SharedPreferences.Editor editor = preferencias.edit();

                editor.putString(nuevoUsuario + nuevoContra +"Datos", nuevoUsuario + "/n" + nuevoEmail  );
                editor.commit();

                Intent pantallaLogeo = new Intent(Registro.this, MainRegistro.class);
                startActivity(pantallaLogeo);



            }



        });


}
}

