package com.example.changosconsumidor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity {

    private Spinner spinnerCategorias;
    private EditText campoProducto;
    private Button btnAgregarProd, btnQuitarProd, btnEditarProd;
    //private ArrayList<Categorias> listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        spinnerCategorias = (Spinner) findViewById(R.id.spinnerCategorias);
        campoProducto = (EditText) findViewById(R.id.campoProducto);



    }
}
