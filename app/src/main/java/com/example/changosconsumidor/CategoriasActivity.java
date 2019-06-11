package com.example.changosconsumidor;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CategoriasActivity extends AppCompatActivity {

    private ListView listaCategorias;
    List<String> categorias;
    ArrayAdapter arrAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        final EditText etCategoria = findViewById(R.id.campoCategoria);
        final Button btnAgregarCat = findViewById(R.id.btnAgregarCategoria);
        final Button btnEditarCat = findViewById(R.id.btnEditarCategoria);
        final Button btnEliminarCat = findViewById(R.id.btnEliminarCategoria);
        listaCategorias = findViewById(R.id.listCategorias);

        //Prueba---------------------------------------
        categorias = new ArrayList<>();
        arrAdp = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,categorias);
        listaCategorias.setAdapter(arrAdp);
        listaCategorias.setBackgroundColor(Color.BLACK);
        //---------------------------------------------

        btnAgregarCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorias.add(etCategoria.getText().toString());
                arrAdp.notifyDataSetChanged();
            }
        });


    }
}
