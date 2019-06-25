package com.example.changosconsumidor;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.changosconsumidor.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoriasActivity extends AppCompatActivity {

    private ListView listaCategorias;
    private EditText et_categoria;
    List<String> categorias;
    ArrayAdapter arrAdp;
    private Category cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        et_categoria = (EditText) findViewById(R.id.campoCategoria);
        listaCategorias = (ListView) findViewById(R.id.listCategorias);

        listaCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DeleteModifyCategory.class);
                intent.putExtra("categoria", listaCategorias.getSelectedView().toString());
                startActivity(intent);
            }
        });

        //Prueba---------------------------------------
        categorias = new ArrayList<>();
        arrAdp = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,categorias);
        listaCategorias.setAdapter(arrAdp);
        listaCategorias.setBackgroundColor(Color.BLACK);
        //---------------------------------------------

    }

    public void onClickAgregarCat(View view) {
        if (view.getId() == R.id.btnAgregarCategoria) {
            if (et_categoria.getText().toString().isEmpty()) {
                Toast.makeText(CategoriasActivity.this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            } else {
                cat = new Category();
                //cat.setId();
                cat.setName(et_categoria.getText().toString());
            }
        } else if (view.getId() == R.id.btn_de_cat_a_home) {
            Intent intent = new Intent(CategoriasActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
}
