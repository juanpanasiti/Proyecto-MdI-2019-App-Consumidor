package com.example.changosconsumidor.database;
import android.content.Context;
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
                                "father_id INTEGER" +
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


}//CLASS