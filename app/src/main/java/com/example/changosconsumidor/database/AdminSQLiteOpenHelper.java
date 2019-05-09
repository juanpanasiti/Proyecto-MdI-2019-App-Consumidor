package com.example.changosconsumidor.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;
/*
    NULL: El valor es un valor NULL.
    INTEGER: El valor es un entero con signo, almacenado en 1, 2, 3, 4, 6 u 8 bytes dependiendo de la magnitud del valor.
    REAL: El valor es un valor de punto flotante, almacenado como un número de punto flotante IEEE de 8 bytes.
    TEXTO: El valor es una cadena de texto, almacenada utilizando la codificación de la base de datos (UTF-8, UTF-16BE o UTF-16LE).
    BLOB: El valor es una gota de datos, almacenados exactamente como se introdujo.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear tabla
        //db.execSQL("CREATE TABLE nombre_tabla(nombre_col tipo_col options)");

        //Categories
        db.execSQL("CREATE TABLE categories(id int PRIMARY KEY AUTOINCREMENT, name text NOT NULL, father int)");
        //Products
        db.execSQL("CREATE TABLE products(id int PRIMARY KEY AUTOINCREMENT, mark text NOT NULL, name text NOT NULL, contentQuantity real NOT NULL, contentUnit text NOT NULL, category int NOT NULL)");
        //BuyLists
        db.execSQL("CREATE TABLE buylists(id int PRIMARY KEY AUTOINCREMENT, name text NOT NULL, date text NOT NULL)");
        //Items
        db.execSQL("CREATE TABLE items(id int PRIMARY KEY AUTOINCREMENT, product int NOT NULL, list int NOT NULL, quantity real NOT NULL DEFAULT 0, unitPrice real NOT NULL, inCart int NOT NULL DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}