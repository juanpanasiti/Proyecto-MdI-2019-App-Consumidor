package com.example.changosconsumidor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import com.example.changosconsumidor.model.Category;
import com.example.changosconsumidor.model.Product;

public class ProductDBHelper extends AdminSQLiteOpenHelper{
    private static final String DB_TABLE = "products";//nombre de la tabla

    public ProductDBHelper(Context context) {
        super(context);
    }

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
        String mark = product.getMark();
        String name = product.getName();
        float contentQuantity = product.getContentQuantity();
        String contentUnit = product.getContentUnit();
        int categoryID = product.getCategory().getId();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        //SQLiteStatement stmt = new
        try{
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
    }//updateProduct()

    //Buscar un registro por ID
    public Product findByID(int id, Context context){
        Product product = new Product();
        String[] columns = {"id","mark","name","contentQuantity","contentUnit","category_id"};
        Cursor cursor = super.findByID(DB_TABLE,id,columns);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            product.setID(cursor.getInt(0));
            product.setMark(cursor.getString(1));
            product.setName(cursor.getString(2));
            product.setContentQuantity(cursor.getFloat(3));
            product.setContentUnit(cursor.getString(4));
            CategoryDBHelper catDBH = new CategoryDBHelper(context);
            product.setCategory(catDBH.findByID(cursor.getInt(5)));
        }
        return product;
    }
}
