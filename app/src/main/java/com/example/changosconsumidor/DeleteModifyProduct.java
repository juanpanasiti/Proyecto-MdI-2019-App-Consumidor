package com.example.changosconsumidor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.changosconsumidor.model.Product;

public class DeleteModifyProduct extends AppCompatActivity {

    private EditText campoProductoDelMod;
    private Product prod;
    private int idExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_modify_product);

        campoProductoDelMod = (EditText) findViewById(R.id.campoProductoDelMod);

        idExtra = getIntent().getIntExtra("id",0);

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
                prod.modificar(DeleteModifyProduct.this);
                break;
            case R.id.btnEliminarProducto:
                prod.borrarReg(DeleteModifyProduct.this);
                break;
        }

    }

    public void buscarId(int idExtra) {
        //prod = prod.buscar(idExtra);
    }
}
