package com.example.changosconsumidor.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;
/*
    NULL: El valor es un valor NULL.
    INTEGER: El valor es un entero con signo, almacenado en 1, 2, 3, 4, 6 u 8 bytes dependiendo de la magnitud del valor.
    REAL: El valor es un valor de punto flotante, almacenado como un número de punto flotante IEEE de 8 bytes.
    TEXT: El valor es una cadena de texto, almacenada utilizando la codificación de la base de datos (UTF-8, UTF-16BE o UTF-16LE).
    BLOB: El valor es una gota de datos, almacenados exactamente como se introdujo.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    //V. globales
    private static int DB_VERSION = 1;
    private static String DB_FILE_NAME = "changosdb";

    public AdminSQLiteOpenHelper(Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    //Crear base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabla CATEGORIAS
        String sqlCategories = "CREATE TABLE categories(" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name TEXT NOT NULL," +
                                ")";

        //Tabla PRODUCTS
        String sqlProducts = "CREATE TABLE products(" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "mark TEXT NOT NULL," +
                                "name TEXT NOT NULL," +
                                "contentQuantity REAL NOT NULL," +
                                "contentUnit TEXT NOT NULL," +
                                "category_id INTEGER NOT NULL" +
                                ")";

        //Tabla BUYLISTS
        String sqlBuyLists = "CREATE TABLE buylists(" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name TEXT NOT NULL," +
                                "date TEXT NOT NULL" +
                                ")";
        //Tabla ITEMS
        String sqlItems = "CREATE TABLE items(" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "product_id INTEGER," +
                                "list_id INTEGER," +
                                "quantity REAL NOT NULL DEFAULT 0," +
                                "unitPrice REAL NOT NULL," +
                                "inCart INTEGER NOT NULL DEFAULT 0" +
                                ")";
        //db.execSQL("CREATE TABLE nombre_tabla(nombre_col tipo_col options)");

        db.execSQL(sqlCategories);
        db.execSQL(sqlProducts);
        db.execSQL(sqlBuyLists);
        db.execSQL(sqlItems);
    }//onCreate()

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }//onUpgrade()

    public void create(ContentValues record, String tableName){
        //crea un nuevo registro en la tabla TableName
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(tableName,null, record);
    }//create()

     public void update(ContentValues record, String tableName, String whereClause, String[] whereArgs){
        //Actualizar los registros de la base de datos tableName que cumplan con las condiciones
        // de whereClause y whereArgs
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(tableName,record,whereClause,whereArgs);
     }//update()

     public void delete(String tableName, String whereClause, String[] whereArgs){
        //Elimina los registros de la base de datos de la tabla tableName que cumplan con las condiciones
        // de whereClause y whereArgs, puede ser uno o muchos registros.. o ninguno
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, whereClause, whereArgs);
     }//delete()

     public Cursor findByID(String tableName, int id, String[] columns){
        //Trae un solo registro de la base de datos de la tabla tableName según su ID
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tableName, columns, "id=" + id, null,null,null,null,"1");
        return cursor;
     }//findByID()

     public Cursor findAll(String tableName, String[] columns){
        //Consulta a la base de datos por todos los registros de cierta tabla (tableName) y columnas seleccionadas
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tableName, columns,null,null,null,null,null);
        return cursor;
     }//findAll()

     public Cursor findByFilter(String tableName, String[] columns, String selection, String[] selectionArgs){
        //Consulta a la base de datos por los registros de la tabla tableName que cumplen con las condiciones de selectionArgs
        // de las columnas de selection.
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tableName,columns,selection,selectionArgs,null,null,null);
        return cursor;
     }//findByFilter()

}//CLASS