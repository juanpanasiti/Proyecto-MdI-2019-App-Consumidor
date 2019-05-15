package com.example.changosconsumidor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import com.example.changosconsumidor.modelo.Category;

import java.util.ArrayList;
import java.util.List;

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

    /*
    //Update data into table
    public void updateData(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("UPDATE student_info SET name=?, age=?, class_name=?, city=? "+
                "WHERE id = ?");
        stmt.bindString(1, student.getName());
        stmt.bindLong(2, student.getAge());
        stmt.bindString(3, student.getClassName());
        stmt.bindString(4, student.getCity());
        stmt.bindLong(5, student.getId());
        stmt.execute();
        stmt.close();
        db.close();
    }
    */
    //Actualizar un registro de categoría
    public static boolean updateCategory(Category cat, Context context){
        String name = cat.getName();
        int fatherID = cat.getFatherID();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        SQLiteStatement stmt = db.compileStatement("UPDATE " + DB_TABLE + " SET name=?, fatherID=?" + "WHERE id = ?");

        stmt.bindString(1, name);
        if(fatherID < 1){
            stmt.bindNull(2);
        } else {
            stmt.bindLong(2,fatherID);
        }//if-else
        try{
            stmt.execute();
            stmt.close();
            db.close();
            Toast.makeText(context, "Categoría " + name + " actualizada.", Toast.LENGTH_SHORT).show();
            return true;
        }catch(Exception e){
            Toast.makeText(context, "Error al actualizar la categoría: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }//updateCategory()

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

    //Delete data from the table for the given id
    /*
    public void deleteData(int stdId){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("DELETE FROM student_info WHERE id = ?");
        stmt.bindLong(1, stdId);
        stmt.execute();
        stmt.close();
        db.close();
    }
    */
    public static void deleteCategory(int catID, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("DELETE FROM " + DB_TABLE + " WHERE id = ?");
        stmt.bindLong(1,catID);
        try{
            stmt.execute();
            stmt.close();
            db.close();
            Toast.makeText(context, "Categoría eliminada correctamente.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            stmt.close();
            db.close();
            Toast.makeText(context, "Error al eliminar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//deleteCategory()

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
