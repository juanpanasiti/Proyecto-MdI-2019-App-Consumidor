package com.example.changosconsumidor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import com.example.changosconsumidor.model.Category;
import com.example.changosconsumidor.model.Product;

public class ProductDBHelper {
    private static final String DB_TABLE = "products";//nombre de la tabla
    /*
    private int id;
    private String mark;
    private String name;
    private float contentQuantity;
    private String contentUnit;
    private Category category;

    //Tabla PRODUCTS
        String sqlProducts = "CREATE TABLE products(" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "mark TEXT NOT NULL," +
                                "name TEXT NOT NULL," +
                                "contentQuantity REAL NOT NULL," +
                                "contentUnit TEXT NOT NULL," +
                                "category_id INTEGER NOT NULL" +
                                ")";
     */
    public static void createProduct(Product product, Context context){
        try{
            int id = product.getID();
            String mark = product.getMark();
            String name = product.getName();
            float contentQuantity = product.getContentQuantity();
            String contentUnit = product.getContentUnit();
            int categoryID = product.getCategory().getId();

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues record = new ContentValues();
            record.put("mark",mark);
            record.put("name",name);
            record.put("contentQuantity", contentQuantity);
            record.put("contentUnit",contentUnit);
            record.put("category_id", categoryID);

            db.insert(DB_TABLE,null,record);
            db.close();

            Toast.makeText(context, "Producto agregado.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//createProduct();

    public static void updateProduct(Product product, Context context){
        int id = product.getID();
        String mark = product.getMark();
        String name = product.getName();
        float contentQuantity = product.getContentQuantity();
        String contentUnit = product.getContentUnit();
        int categoryID = product.getCategory().getId();

        panasAdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        //SQLiteStatement stmt = new
        try{
            ContentValues record = new ContentValues();
            record.put("id", id);
            record.put("mark",mark);
            record.put("name",name);
            record.put("contentQuantity", contentQuantity);
            record.put("contentUnit",contentUnit);
            record.put("category_id", categoryID);

            db.insert(DB_TABLE,null,record);
            db.close();

            Toast.makeText(context, "Producto agregado.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//updateProduct()
}
