package com.example.changosconsumidor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final TextView etNombre = (EditText) findViewById(R.id.etNombreUsuario);
        final TextView etContra = (EditText) findViewById(R.id.etContraseña);
        Button btnLogear = (Button) findViewById(R.id.btnLogear);
        Button btnRegistrar = (Button) findViewById(R.id.btnCrearUsuario);


        btnLogear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etNombre.getText().toString();
                String contraseña = etContra.getText().toString();

                SharedPreferences preferencias = getSharedPreferences("Mis Preferencias", MODE_PRIVATE);

                String detallesUsuario = preferencias.getString(usuario + contraseña + "datos", "Usuario o contraseña incorrectos");
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("mostrar", detallesUsuario);
                editor.commit();

                Intent pantallaMuestra = new Intent(MainRegistro.this,  PantallaDeMuestra.class);
                startActivity(pantallaMuestra);

            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaRegisttro = new Intent(MainRegistro.this, Registro.class);
                startActivity(pantallaRegisttro);
            }
        });

}
}