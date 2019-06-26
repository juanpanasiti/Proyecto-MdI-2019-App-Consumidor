package com.example.changosconsumidor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.changosconsumidor.model.Product;

public class DeleteModifyProduct extends AppCompatActivity {

    private EditText campoProductoMod, campoMarcaMod, campoContentQuantityMod, campoContentUnitMod;
    private Product prod;
    private int idExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_modify_product);

        campoProductoMod = (EditText) findViewById(R.id.campoProductoMod);
        campoMarcaMod = (EditText) findViewById(R.id.campoMarcaMod);
        campoContentQuantityMod = (EditText) findViewById(R.id.campoContentQuantityMod);
        campoContentUnitMod = (EditText) findViewById(R.id.campoContentUnitMod);

        idExtra = getIntent().getIntExtra("id",0);
        buscarId(idExtra);

        campoProductoMod.setText(prod.getName());
        campoMarcaMod.setText(prod.getMark());
        campoContentQuantityMod.setText(String.valueOf(prod.getContentQuantity()));
        campoContentUnitMod.setText(prod.getContentUnit());

    }

    public void onClickDeleteModifyProd(View view) {
        prod = new Product();
        prod.setID(idExtra);
        switch (view.getId()) {
            case R.id.btn_de_prod_a_cat:
                Intent intent = new Intent(DeleteModifyProduct.this, DeleteModifyCategory.class);
                startActivity(intent);
                break;
            case R.id.btnModificarProducto:
                prod.setName(campoProductoMod.getText().toString());
                prod.setMark(campoMarcaMod.getText().toString());
                prod.setContentQuantity((float)Double.parseDouble(campoContentQuantityMod.getText().toString()));
                prod.setContentUnit(campoContentUnitMod.getText().toString());
                prod.modificar(DeleteModifyProduct.this);
                break;
            case R.id.btnEliminarProducto:
                //prod.borrarReg(DeleteModifyProduct.this, idExtra);
                break;

        }

    }

    public void buscarId(int id) {
        prod = prod.buscar(DeleteModifyProduct.this, id);
    }
}
