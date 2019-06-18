package com.example.changosconsumidor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import com.example.changosconsumidor.model.Category;
import com.example.changosconsumidor.model.Product;

import java.util.ArrayList;

public class ProductDBHelper extends AdminSQLiteOpenHelper{
    private static final String DB_TABLE = "products";//nombre de la tabla
    private static final String[] DB_ALL_COLUMNS = {"id","mark","name","contentQuantity","contentUnit","category_id"};

    public ProductDBHelper(Context context) {
        super(context);
    }

    public void createProduct(Product product, Context context){
        try{
            String mark = product.getMark();
            String name = product.getName();
            float contentQuantity = product.getContentQuantity();
            String contentUnit = product.getContentUnit();
            int categoryID = product.getCategory().getId();

            ContentValues record = new ContentValues();
            record.put("mark",mark);
            record.put("name",name);
            record.put("contentQuantity", contentQuantity);
            record.put("contentUnit",contentUnit);
            record.put("category_id", categoryID);

            super.create(record,DB_TABLE);

            Toast.makeText(context, "Producto agregado.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//createProduct();

    public void updateProduct(Product product, Context context){
        String mark = product.getMark();
        String name = product.getName();
        float contentQuantity = product.getContentQuantity();
        String contentUnit = product.getContentUnit();
        int categoryID = product.getCategory().getId();

        String whereClause = "id=";
        String[] whereArgs = {String.valueOf(product.getID())};

        try{
            ContentValues record = new ContentValues();
            record.put("mark",mark);
            record.put("name",name);
            record.put("contentQuantity", contentQuantity);
            record.put("contentUnit",contentUnit);
            record.put("category_id", categoryID);

            super.update(record,DB_TABLE,whereClause,whereArgs);

            Toast.makeText(context, "Producto agregado.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//updateProduct()

    //Buscar un registro por ID
    public Product findByID(int id, Context context){
        Product product = new Product();
        Cursor cursor = super.findByID(DB_TABLE,id,DB_ALL_COLUMNS);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            product.setID(cursor.getInt(0));
            product.setMark(cursor.getString(1));
            product.setName(cursor.getString(2));
            product.setContentQuantity(cursor.getFloat(3));
            product.setContentUnit(cursor.getString(4));
            CategoryDBHelper catDBH = new CategoryDBHelper(context);
            product.setCategory(catDBH.findByID(cursor.getInt(5),context));
        }
        return product;
    }//findByID()

    //Borrar un registro
    public void deleteProduct(Product product, Context context){
        try{
            super.delete(DB_TABLE, "id=" + product.getID(), null);
            Toast.makeText(context, "Producto borrado correctamente", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Error al borrar el producto: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//deleteProduct()

    public ArrayList<Product> getAll(Context context){
        ArrayList<Product> products = new ArrayList<>();

        Cursor cursor = super.findAll(DB_TABLE,DB_ALL_COLUMNS);
        while (cursor.moveToNext()){
            Product prod = new Product();

            prod.setID(cursor.getInt(0));
            prod.setMark(cursor.getString(1));
            prod.setName(cursor.getString(2));
            prod.setContentQuantity(cursor.getFloat(3));
            prod.setContentUnit(cursor.getString(4));
            CategoryDBHelper catDBH = new CategoryDBHelper(context);
            prod.setCategory(catDBH.findByID(cursor.getInt(5), context));

            products.add(prod);
        }
        return products;
    }//getAll()

    public ArrayList<Product> findByCategory(Category category, Context context){
        ArrayList<Product> products = new ArrayList<>();
        String selection = "category_id=";
        String[] selectionArgs = {String.valueOf(category.getId())};

        Cursor cursor = super.findByFilter(DB_TABLE,DB_ALL_COLUMNS,selection,selectionArgs);

        while(cursor.moveToNext()){
            Product prod = new Product();

            prod.setID(cursor.getInt(0));
            prod.setMark(cursor.getString(1));
            prod.setName(cursor.getString(2));
            prod.setContentQuantity(cursor.getFloat(3));
            prod.setContentUnit(cursor.getString(4));
            CategoryDBHelper catDBH = new CategoryDBHelper(context);
            prod.setCategory(catDBH.findByID(cursor.getInt(5),context));

            products.add(prod);
        }

        return products;
    }//findByCategory()
}
