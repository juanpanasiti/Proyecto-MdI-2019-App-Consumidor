package com.example.changosconsumidor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.changosconsumidor.model.Category;

import java.util.ArrayList;

public class CategoryDBHelper extends AdminSQLiteOpenHelper{
    private static final String DB_TABLE = "categories";//nombre de la tabla
    private static final String[] DB_ALL_COLUMNS = {"id","name"};

    public CategoryDBHelper(Context context) {
        super(context);
    }

    public void createCategory(Category category, Context context){
        try{
            ContentValues record = new ContentValues();
            record.put("name",category.getName());

            super.create(record,DB_TABLE);

            Toast.makeText(context, "Categoría agregada.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//createCategory();

    public void updateCategory(Category category, Context context){
        String whereClause = "id=";
        String[] whereArgs = {String.valueOf(category.getId())};

        try{
            ContentValues record = new ContentValues();
            record.put("name",category.getName());

            super.update(record,DB_TABLE,whereClause,whereArgs);

            Toast.makeText(context, "Categoría agregada.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//updateCategory()

    //Buscar un registro por ID
    public Category findByID(int id, Context context){
        Category category = new Category();
        Cursor cursor = super.findByID(DB_TABLE,id,DB_ALL_COLUMNS);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
        }
        return category;
    }//findByID()

    //Borrar un registro
    public void deleteCategory(Category category, Context context){
        try{
            super.delete(DB_TABLE, "id=" + category.getId(), null);
            Toast.makeText(context, "Categoría borrada correctamente", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Error al borrar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//deleteCategory()

    public ArrayList<Category> getAll(Context context){
        ArrayList<Category> categories = new ArrayList<>();

        Cursor cursor = super.findAll(DB_TABLE,DB_ALL_COLUMNS);
        while (cursor.moveToNext()){
            Category category = new Category();

            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));

            categories.add(category);
        }
        return categories;
    }//getAll()
}//CLASS
