package com.example.changosconsumidor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class AgregarItem extends AppCompatActivity {

    private Spinner spinner_productos, spinner_categorias;
    private EditText campoCantidad, campoPrecio;

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
}
