package com.example.changosconsumidor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import com.example.changosconsumidor.model.Category;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class CategoryDBHelper extends AdminSQLiteOpenHelper{
    private static final String DB_TABLE = "categories";//nombre de la tabla
    //Columnas DB:          id int, name text, father int
    //Atributos Model:      name String, father Category, products ArrayList

    //Equivalencias DB-Model
    // id -> auto
    // name(text) === category.getName()
    // father(int) === category.getFatherID()

    public CategoryDBHelper(Context context) {
        super(context);
    }

    //Otra forma de insertar un elemento a la base de datos
    public void create(Category category, Context context){
        ContentValues record = new ContentValues();
        record.put("name", category.getName());
        record.put("father", category.getFatherID());
        try{
            super.create(record,DB_TABLE);
            Toast.makeText(context, "Categoría creada correctamente", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error al crear categoría: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//create()


    //Actualizar un registro de categoria
    public void update(Category category, Context context){
        ContentValues record = new ContentValues();
        record.put("name", category.getName());
        record.put("father", category.getFatherID());
        try{
            super.update(record, DB_TABLE,"id=" + category.getId(), null);
            Toast.makeText(context, "Categoría actualizada correctamente", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Error al actualizar la categoría: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //Borrar un registro
    public void delete(Category category, Context context){
        try{
            super.delete(DB_TABLE, "id=" + category.getId(), null);
            Toast.makeText(context, "Categoría borrada correctamente", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Error al borrar la categoría: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //Buscar un registro por ID
    public Category findByID(int id){
        Category category = new Category();
        String[] columns = {"id","name","father"};
        Cursor cursor = super.findByID(DB_TABLE,id,columns);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            if(cursor.getInt(2) > 0){
                category.setFather(this.findByID(cursor.getInt(2)));
            }
        }
        return category;
    }


    /*
    //Select all data from the table
	public List getStudents() {
		List students = new ArrayList();
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT id, name, age, class_name, city from student_info ORDER BY id ASC";
		Cursor cursor = db.rawQuery(query, null);
		while (cursor.moveToNext()) {
			Student std = new Student();
			std.setId(cursor.getInt(0));
			std.setName(cursor.getString(1));
			std.setAge(cursor.getInt(2));
			std.setClassName(cursor.getString(3));
			std.setCity(cursor.getString(4));
			students.add(std);
		}
		db.close();
		return students;
	}
    */

    public static ArrayList<Category> getCategories(Context context) {
        ArrayList<Category> categories = new ArrayList<>();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getReadableDatabase();//Acceso de solo lectura a la BD
        String query = "SELECT name FROM " + DB_TABLE + " ORDER BY name ASC";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Category cat = new Category();
            cat.setName(cursor.getString(0));
            categories.add(cat);
        }
        db.close();
        return categories;
    }//getCategories()

    public static ArrayList<Category> getAll(Context context){
        ArrayList<Category> categories = new ArrayList<>();
        String[] columns = {"name"};

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getReadableDatabase();//Acceso de solo lectura a la BD

        Cursor c = db.query(DB_TABLE,columns,null,null,null,null,"name");
        while (c.moveToNext()){
            Category cat = new Category();
            cat.setName(c.getString(0));
            categories.add(cat);
        }
        db.close();
        return categories;
    }


    //Select data for the given id
    /*
    public Student getStudentById(int stdId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id, name, age, class_name, city FROM student_info WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(stdId)});
        cursor.moveToFirst();
        Student std = new Student();
        std.setId(cursor.getInt(0));
        std.setName(cursor.getString(1));
        std.setAge(cursor.getInt(2));
        std.setClassName(cursor.getString(3));
        std.setCity(cursor.getString(4));
        db.close();
        return std;
    }
    */
    public Category getCategory(int catID, Context context){
        Category category = new Category();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getReadableDatabase();
        String query = "SELECT id, name, father FROM " + DB_TABLE + " WHERE id = ?";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        //category.setID(cursor.getInt(0);
        category.setName(cursor.getString(1));
        if(cursor.getInt(2) > 0)
            category.setFather(this.getCategory(cursor.getInt(2),context));
        return category;
    }//getCategory()
    //Consulta cantidad de registros
    public static int countCategories(Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor c = db.query("categories", null, null, null, null, null, null);
        return c.getCount();
    }//countCategories()
}//CLASS
