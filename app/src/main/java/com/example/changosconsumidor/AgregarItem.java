package com.example.changosconsumidor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.changosconsumidor.model.Category;
import com.example.changosconsumidor.model.Item;
import com.example.changosconsumidor.model.Product;

import java.util.ArrayList;

public class AgregarItem extends AppCompatActivity {

    private Spinner spinner_productos, spinner_categorias;
    private EditText campoCantidad, campoPrecio;

    private Product prod = new Product();
    private ArrayList<Product> arrProductos;
    private ArrayList<String> arrProductosStr;

    private Category cat = new Category();
    private ArrayList<Category> arrCategorias;
    private ArrayList<String> arrCategoriasStr;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_item);

        spinner_productos = (Spinner) findViewById(R.id.spinnerProductoDeItem);
        spinner_categorias = (Spinner) findViewById(R.id.spinnerCategoriasDeItem);

    }

    public void onClickAgregarItem(View view) {
        Intent intent = null;
        switch(view.getId()) {
            case R.id.btn_de_agregarItem_a_list:

                intent = new Intent();
                break;
            case R.id.btnAgregarItemFinal:
                intent = new Intent();
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    private void rellenarSpinnerCategorias() {
        arrCategorias = new ArrayList<>();
        arrCategorias = cat.traerTodo(AgregarItem.this);
        arrCategoriasStr = new ArrayList<>();
        for(int i = 0; i < arrCategorias.size(); i++) {
            arrCategoriasStr.add(arrCategorias.get(i).getName());
        }
        ArrayAdapter<String> categorias = new ArrayAdapter<>(AgregarItem.this, android.R.layout.simple_spinner_item, arrCategoriasStr);
        spinner_categorias.setAdapter(categorias);
    }

    public void llenarSpinnerProductos() {

    }

}
