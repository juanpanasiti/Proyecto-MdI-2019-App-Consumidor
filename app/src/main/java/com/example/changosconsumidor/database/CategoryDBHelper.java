package com.example.changosconsumidor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

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
    //Insert data into table
    /*Ejemplo de método para insertar un nuevo registro a la base de datos
        public void insertData(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO student_info (name, age, class_name, city) " +
                "VALUES (?,?,?,?)");
        stmt.bindString(1, student.getName());
        stmt.bindLong(2, student.getAge());
        stmt.bindString(3, student.getClassName());
        stmt.bindString(4, student.getCity());
        stmt.execute();
        stmt.close();
        db.close();
    }*/

    //método para instertar un elemento a la base de datos
    public static boolean insertCategory(Category cat, Context context){
        String name = cat.getName();
        int fatherID = cat.getFatherID();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO " + DB_TABLE + "(name, fatherID) " +
                "VALUES (?,?)");
        stmt.bindString(1,name);
        if(fatherID < 1){
            stmt.bindNull(2);
        } else {
            stmt.bindLong(2,fatherID);
        }
        try{
            stmt.execute();
            stmt.close();
            db.close();
            Toast.makeText(context, "Agregada nueva categoría " + name, Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            stmt.close();
            db.close();
            Toast.makeText(context, "Error al cargar categoría " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }//try-catch

    }//insertCategory()

    //Otra forma de insertar un elemento a la base de datos
    public static boolean createCategory(Category category, Context context){
        try{
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("name", category.getName());

            db.insert(DB_TABLE, null, registro);
            db.close();

            Toast.makeText(context, "Agregada nueva categoría " + category.getName(), Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            Toast.makeText(context, "Error al cargar categoría " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }//try-catch
    }//createCategory()

    //Consulta cantidad de registros
    public static int countCategories(Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor c = db.query("categories", null, null, null, null, null, null);
        return c.getCount();
    }//countCategories()
}//CLASS
