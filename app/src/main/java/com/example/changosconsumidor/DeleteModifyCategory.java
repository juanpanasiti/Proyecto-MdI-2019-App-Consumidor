package com.example.changosconsumidor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.changosconsumidor.database.AdminSQLiteOpenHelper;
import com.example.changosconsumidor.database.CategoryDBHelper;
import com.example.changosconsumidor.model.Category;

public class DeleteModifyCategory extends AppCompatActivity {

    private String categoryExtra;
    private EditText category;
    private Category cat;
    CategoryDBHelper admin = new CategoryDBHelper(DeleteModifyCategory.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_modify_category);

        category = (EditText) findViewById(R.id.campoCategoriaDelMod);
        categoryExtra = getIntent().getStringExtra("categoria");
        category.setText(categoryExtra);

        /*
        Buscar ID en la base de datos para hacer la modificacion o eliminar, ya que las categorias no se puede repetir por nombre exacto
         */
    }

    public void onClickDeleteModify(View view) {
        if (view.getId() == R.id.btnModificarCategoria) {
            cat = new Category();
            //cat.setId(); ID de la categoria
            cat.setName(category.getText().toString());
            admin.updateCategory(cat, DeleteModifyCategory.this);
        } else if (view.getId() == R.id.btnEliminarCategoria) {
            cat = new Category();
            //cat.setId();
            cat.setName(category.getText().toString());
            admin.deleteCategory(cat, DeleteModifyCategory.this);
        }
    }
}
