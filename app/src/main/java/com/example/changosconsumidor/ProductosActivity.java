package com.example.changosconsumidor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.changosconsumidor.database.AdminSQLiteOpenHelper;
import com.example.changosconsumidor.model.Category;

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity {

    private Spinner spinnerCategorias;
    private EditText campoProducto;
    private Button btnAgregarProd, btnQuitarProd, btnEditarProd;
    private ArrayList<Category> listaCategorias; //ArrayList con los objetos de categorias
    private ArrayList<String> listaNombreCategorias; //Solo guarda los nombres de las categorias

    AdminSQLiteOpenHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        //conn = new AdminSQLiteOpenHelper(getApplicationContext(),"changosDB",null,1);

        spinnerCategorias = (Spinner) findViewById(R.id.spinnerCategorias);
        campoProducto = (EditText) findViewById(R.id.campoProducto);

        obtenerListaCategorias();

        //Cargar en el Spinner de categorias
        ArrayAdapter<CharSequence> arrayCategorias = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaNombreCategorias);
        spinnerCategorias.setAdapter(arrayCategorias);
        spinnerCategorias.

        //Recordar sobre escribir el metodo toString en el getName del objeto
        ArrayAdapter<Category> arrayObjCats = new ArrayAdapter<Category>(this,R.layout.support_simple_spinner_dropdown_item,listaCategorias);
        spinnerCategorias.setAdapter(arrayObjCats);

    }

    private void obtenerListaCategorias() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Category category = null;
        listaCategorias  = new ArrayList<Category>();

        //Consulta a la base de datos, devuelve la lista consultada en la tabla
        Cursor cursor = db.rawQuery("SELECT * FROM" + Category.all(), null);

        //Iterar hasta que se acaben los registros en la base de datos
        while (cursor.moveToNext()) {
            category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            //No va a ir
            //category.setFather(new Category());

            listaCategorias.add(category);
        }
        rellenarListaCategorias();
    }

    private void rellenarListaCategorias() {
        listaNombreCategorias = new ArrayList<String>();
        listaNombreCategorias.add("Seleccione una categoría...");

        for (int i = 0; i < listaNombreCategorias.size(); i++) {
            listaNombreCategorias.add(listaCategorias.get(i).getName());
        }
    }

}
