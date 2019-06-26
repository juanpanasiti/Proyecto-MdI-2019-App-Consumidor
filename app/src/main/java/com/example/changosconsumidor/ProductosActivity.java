package com.example.changosconsumidor;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.changosconsumidor.database.AdminSQLiteOpenHelper;
import com.example.changosconsumidor.database.ProductDBHelper;
import com.example.changosconsumidor.model.Category;
import com.example.changosconsumidor.model.Product;

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity {

    private Spinner spinnerCategorias;
    private EditText campoProducto, campoMarca, campoContentUnit, campoContentQuantity;
    private Product prod = new Product();
    private Category cat = new Category();

    private ArrayList<Category> arrCategorias;
    ArrayList<String> arrCategoriasStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        spinnerCategorias = (Spinner) findViewById(R.id.spinnerCategorias);
        campoProducto = (EditText) findViewById(R.id.campoProducto);
        campoMarca = (EditText) findViewById(R.id.campoMarca);
        campoContentQuantity = (EditText) findViewById(R.id.campoContentQuantity);
        campoContentUnit = (EditText) findViewById(R.id.campoContentUnit);

    }

    public void onClickAgregarProd(View view) {
        if (view.getId() == R.id.btnAgregarProducto) {
            if (campoProducto.getText().toString().isEmpty()) {
                Toast.makeText(ProductosActivity.this, "Campo Producto Vacio", Toast.LENGTH_SHORT).show();
            } else {
                prod.setName(campoProducto.getText().toString());
                prod.setCategory(arrCategorias.get(spinnerCategorias.getSelectedItemPosition()));
                prod.setContentQuantity((float)Double.parseDouble(campoContentQuantity.getText().toString()));
                prod.setContentUnit(campoContentUnit.getText().toString()); //Tipo de unidad de medidad
                prod.setMark(campoMarca.getText().toString());
            }
        } else if(view.getId() == R.id.btn_de_prod_a_home) {
            Intent intent = new Intent(ProductosActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    private void rellenarSpinnerCategorias() {
        arrCategorias = cat.traerTodo(ProductosActivity.this);
        for(int i = 0; i < arrCategorias.size(); i++) {
            arrCategoriasStr.add(arrCategorias.get(i).getName());
        }
        ArrayAdapter<String> categorias = new ArrayAdapter<>(ProductosActivity.this, android.R.layout.simple_spinner_item, arrCategoriasStr);
        spinnerCategorias.setAdapter(categorias);
    }

}
