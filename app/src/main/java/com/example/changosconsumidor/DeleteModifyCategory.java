package com.example.changosconsumidor;

import android.content.Intent;
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

    private String categoryExtra;
    private EditText category;
    private ListView listaProductos;
    private TextView categoriaMostrar;
    private Category cat;
    private Product prod = new Product();
    private ArrayList<Product> arrProductos;
    private ArrayList<String> arrProductosStr;
    ArrayAdapter arrAdp;
    private int idExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_modify_category);

        categoriaMostrar = (TextView) findViewById(R.id.categoriaMostrar);
        category = (EditText) findViewById(R.id.campoCategoriaDelMod);
        listaProductos = (ListView) findViewById(R.id.listProductos);

        categoryExtra = getIntent().getStringExtra("categoria");
        idExtra = getIntent().getIntExtra("id",0);
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

        category.setText(categoryExtra);
        categoriaMostrar.setText(categoryExtra);
    }

    public void onClickDeleteModifyCat(View view) {
        switch (view.getId()) {
            case R.id.btnModificarCategoria:
                cat = new Category();
                cat.setId(idExtra);
                cat.setName(category.getText().toString());
                //cat.updateCategory(cat, DeleteModifyCategory.this);
                break;
            case R.id.btnEliminarCategoria:
                cat = new Category();
                cat.setId(idExtra);
                //cat.deleteCategory(cat, DeleteModifyCategory.this);
                break;
            case R.id.btnDeleteModCat_NewCat:
                Intent intent = new Intent(DeleteModifyCategory.this, CategoriasActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void llenarListaProducto() {
        cat = new Category();
        cat.setId(idExtra);
        arrProductos = prod.traerTodo(DeleteModifyCategory.this, cat);
        arrProductosStr = new ArrayList<>();
        for (int i = 0; i < arrProductos.size(); i++) {
            arrProductosStr.add(arrProductos.get(i).getName());
        }
        arrAdp = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrProductosStr);
        listaProductos.setAdapter(arrAdp);
    }
}
