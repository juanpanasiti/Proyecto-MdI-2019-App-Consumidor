package com.example.changosconsumidor;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.changosconsumidor.database.CategoryDBHelper;
import com.example.changosconsumidor.model.Category;

import java.util.ArrayList;

public class CategoriasActivity extends AppCompatActivity {

    private ListView listaCategorias;
    private EditText et_categoria;
    private ArrayList<Category> arrCategorias;
    ArrayList<String> arrCategoriasStr;
    ArrayAdapter arrAdp;
    private Category cat;
    private CategoryDBHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        et_categoria = (EditText) findViewById(R.id.campoCategoria);
        listaCategorias = (ListView) findViewById(R.id.listCategorias);

        llenarListaCategorias();

        listaCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getApplicationContext(), DeleteModifyCategory.class);
                intent.putExtra("categoria", arrCategoriasStr.get(i));
                intent.putExtra("id", arrCategorias.get(i).getId());
                startActivity(intent);
            }
        });


    }

    public void onClickAgregarCat(View view) {
        if (view.getId() == R.id.btnAgregarCategoria) {
            if (et_categoria.getText().toString().isEmpty()) {
                Toast.makeText(CategoriasActivity.this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            } else {
                cat = new Category();
                cat.setName(et_categoria.getText().toString());
                admin.createCategory(cat, CategoriasActivity.this);
            }
        } else if (view.getId() == R.id.btn_de_cat_a_home) {
            Intent intent = new Intent(CategoriasActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public void llenarListaCategorias() {
        //arrCategorias = cat.traerTodo(CategoriasActivity.this);
        for(int i = 0; i < arrCategorias.size(); i++) {
            arrCategoriasStr.add(arrCategorias.get(i).getName());
        }
        arrAdp = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrCategoriasStr);
        listaCategorias.setAdapter(arrAdp);
    }
}
