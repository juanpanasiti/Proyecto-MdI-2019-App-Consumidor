package com.example.changosconsumidor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PantallaDeMuestra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_info);

        SharedPreferences preferencias = getSharedPreferences("Mis Preferencias", MODE_PRIVATE);
        String mostrar = preferencias.getString("mostrar","");

        TextView mostrarInfo = (TextView) findViewById(R.id.txtDatos);
        mostrarInfo.setText(mostrar);

}
}