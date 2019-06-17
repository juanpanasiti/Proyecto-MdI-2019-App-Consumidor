package com.example.changosconsumidor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.changosconsumidor.model.BuyList;

import java.util.ArrayList;

public class BuyListDBHelper extends AdminSQLiteOpenHelper {
    private static final String DB_TABLE = "buylists";//nombre de la tabla
    private static final String[] DB_ALL_COLUMNS = {"id","name","date"};
    public BuyListDBHelper(Context context) {
        super(context);
    }
    public void createBuyList(BuyList buyList, Context context){
        try{
            ContentValues record = new ContentValues();
            record.put("name",buyList.getName());
            record.put("date", buyList.getDBDate());

            super.create(record,DB_TABLE);

            Toast.makeText(context, "Lista de compra agregada.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//createBuyList();

    public void updateBuyList(BuyList buyList, Context context){
        String whereClause = "id=";
        String[] whereArgs = {String.valueOf(buyList.getID())};

        try{
            ContentValues record = new ContentValues();
            record.put("name",buyList.getName());
            record.put("date", buyList.getDBDate());

            super.update(record,DB_TABLE,whereClause,whereArgs);

            Toast.makeText(context, "Lista de compra agregada.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//updateBuyList()

    //Buscar un registro por ID
    public BuyList findByID(int id, Context context){
        BuyList buyList = new BuyList();
        Cursor cursor = super.findByID(DB_TABLE,id,DB_ALL_COLUMNS);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            buyList.setID(cursor.getInt(0));
            buyList.setName(cursor.getString(1));
            buyList.setDate(cursor.getString(2));
        }
        return buyList;
    }//findByID()

    //Borrar un registro
    public void deleteBuyList(BuyList buyList, Context context){
        try{
            super.delete(DB_TABLE, "id=" + buyList.getID(), null);
            Toast.makeText(context, "Lista de compra borrada correctamente", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Error al borrar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//deleteBuyList()

    public ArrayList<BuyList> getAll(Context context){
        ArrayList<BuyList> buyLists = new ArrayList<>();

        Cursor cursor = super.findAll(DB_TABLE,DB_ALL_COLUMNS);
        while (cursor.moveToNext()){
            BuyList buyList = new BuyList();

            buyList.setId(cursor.getInt(0));
            buyList.setName(cursor.getString(1));
            buyList.setDate(cursor.getString(2));

            buyLists.add(buyList);
        }
        return buyLists;
    }//getAll()
}
