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
import android.widget.TextView;

import com.example.changosconsumidor.database.AdminSQLiteOpenHelper;
import com.example.changosconsumidor.database.CategoryDBHelper;
import com.example.changosconsumidor.model.Category;
import com.example.changosconsumidor.model.Product;

import java.util.ArrayList;

public class DeleteModifyCategory extends AppCompatActivity {

    private EditText category;
    private ListView listaProductos;
    private TextView categoriaMostrar;
    private Category cat = new Category();
    private Product prod = new Product();
    private ArrayList<Product> arrProductos;
    private ArrayList<String> arrProductosStr;
    ArrayAdapter arrAdp;
    private int idExtra;
    private String categoryExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_modify_category);

        categoriaMostrar = (TextView) findViewById(R.id.categoriaMostrar);
        category = (EditText) findViewById(R.id.campoCategoriaDelMod);
        listaProductos = (ListView) findViewById(R.id.listProductos);

        categoryExtra = getIntent().getStringExtra("categoria");
        idExtra = getIntent().getIntExtra("id",0);
        categoriaMostrar.setText(categoryExtra);
        category.setText(categoryExtra);

        llenarListaProducto();

        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(DeleteModifyCategory.this, DeleteModifyProduct.class);
                intent.putExtra("id", arrProductos.get(i).getID());
                startActivity(intent);
            }
        });

    }

    //Listener de los botones de la activity
    public void onClickDeleteModifyCat(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnModificarCategoria:
                cat.setName(category.getText().toString());
                cat.modificar(DeleteModifyCategory.this, cat);
                intent = new Intent(DeleteModifyCategory.this, CategoriasActivity.class);
                break;
            case R.id.btnEliminarCategoria:
                cat.setId(idExtra);
                cat.borrarReg(DeleteModifyCategory.this, cat);
                intent = new Intent(DeleteModifyCategory.this, CategoriasActivity.class);
                break;
            case R.id.btnDeleteModCat_NewCat:
                intent = new Intent(DeleteModifyCategory.this, CategoriasActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    //Metodo para traer los productos de la categoria seleccionada y llenar con estos el ListView
    public void llenarListaProducto() {
        cat.setId(idExtra);
        arrProductos = new ArrayList<>();
        arrProductos = prod.traerProductsCategory(DeleteModifyCategory.this, cat);
        arrProductosStr = new ArrayList<>();
        for (int i = 0; i < arrProductos.size(); i++) {
            arrProductosStr.add(arrProductos.get(i).getName());
        }
        arrAdp = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrProductosStr);
        listaProductos.setAdapter(arrAdp);
        listaProductos.setBackgroundColor(Color.BLACK);
    }
}
