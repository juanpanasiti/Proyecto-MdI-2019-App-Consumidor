package com.example.changosconsumidor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.changosconsumidor.model.Product;

public class DeleteModifyProduct extends AppCompatActivity {

    private EditText campoProductoMod, campoMarcaMod, campoContentQuantityMod, campoContentUnitMod;
    private TextView productoMostrar;
    private Product prod = new Product();
    private int idExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_modify_product);

        campoProductoMod = (EditText) findViewById(R.id.campoProductoMod);
        campoMarcaMod = (EditText) findViewById(R.id.campoMarcaMod);
        campoContentQuantityMod = (EditText) findViewById(R.id.campoContentQuantityMod);
        campoContentUnitMod = (EditText) findViewById(R.id.campoContentUnitMod);
        productoMostrar = (TextView) findViewById(R.id.productoMostrar);

        /*
        Capturo el ID enviado al seleccionar el producto y busco el si existe el producto, luego set a los atributos del producto
        para usarlos
         */
        idExtra = getIntent().getIntExtra("id",0);
        buscarId(idExtra); //Retorna un producto que lo guardo en la instancia prod, encontrado por el ID

        productoMostrar.setText(prod.getName());
        campoProductoMod.setText(prod.getName());
        campoMarcaMod.setText(prod.getMark());
        campoContentQuantityMod.setText(String.valueOf(prod.getContentQuantity()));
        campoContentUnitMod.setText(prod.getContentUnit());

    }

    public void onClickDeleteModifyProd(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_de_prod_a_cat:
                intent = new Intent(DeleteModifyProduct.this, DeleteModifyCategory.class);
                break;
            case R.id.btnModificarProducto:
                prod.setName(campoProductoMod.getText().toString());
                prod.setMark(campoMarcaMod.getText().toString());
                prod.setContentQuantity((float)Double.parseDouble(campoContentQuantityMod.getText().toString()));
                prod.setContentUnit(campoContentUnitMod.getText().toString());
                prod.modificar(DeleteModifyProduct.this, prod);
                intent = new Intent(DeleteModifyProduct.this, DeleteModifyCategory.class);
                break;
            case R.id.btnEliminarProducto:
                prod.borrarReg(DeleteModifyProduct.this, prod);
                intent = new Intent(DeleteModifyProduct.this, DeleteModifyCategory.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    public void buscarId(int id) {
        prod = prod.buscar(DeleteModifyProduct.this, id);
    }
}
