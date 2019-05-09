package com.example.changosconsumidor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.changosconsumidor.modelo.Category;

public class CategoryDBHelper {
    private static final String DB_NAME = "database";//nombre de la base de datos
    private static final String DB_TABLE = "categories";//nombre de la tabla
    //Columnas DB:          id int, name text, father int
    //Atributos Model:      name String, father Category, products ArrayList

    //Equivalencias DB-Model
    // id -> auto
    // name(text) === category.getName()
    // father(int) === category.getFatherID()

    //Alta de nueva categoría
    public static boolean createCategory(Category category, Context context){
        boolean created = false;
        String name = category.getName();
        String father = category.getFatherID();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, DB_NAME, null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("name", name);
        registro.put("father", father);

        db.insert(DB_TABLE, null, registro);
        return created;
    }
}
