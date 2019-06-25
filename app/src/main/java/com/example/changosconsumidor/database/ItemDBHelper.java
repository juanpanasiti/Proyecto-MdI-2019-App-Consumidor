package com.example.changosconsumidor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.changosconsumidor.model.Item;

import java.util.ArrayList;


public class ItemDBHelper extends AdminSQLiteOpenHelper {
    private static final String DB_TABLE = "items";//nombre de la tabla
    private static final String[] DB_ALL_COLUMNS = {"id","product","buylist","quantity","unitPrice","intoChart"};

    public ItemDBHelper(Context context) {
        super(context);
    }

    public void createItem(Item item, Context context){
        try{
            ContentValues record = new ContentValues();
            record.put("product_id",item.getProduct().getID());//devuelve int
            record.put("list_id", item.getBuyList().getID());//devuelve int
            record.put("quantity", item.getQuantity());// devuelve float
            record.put("unitPrice", item.getUnitPrice()); //devuelve float
            record.put("intoChart", item.isIntoChart()); //devuelve boolean

            super.create(record,DB_TABLE);

            Toast.makeText(context, "Lista de compra agregada.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//createItem();

    public void updateItem(Item item, Context context){
        String whereClause = "id=";
        String[] whereArgs = {String.valueOf(item.getID())};

        try{
            ContentValues record = new ContentValues();
            record.put("product_id",item.getProduct().getID());//devuelve int
            record.put("list_id", item.getBuyList().getID());//devuelve int
            record.put("quantity", item.getQuantity());// devuelve float
            record.put("unitPrice", item.getUnitPrice()); //devuelve float
            record.put("intoChart", item.isIntoChart()); //devuelve boolean

            super.update(record,DB_TABLE,whereClause,whereArgs);

            Toast.makeText(context, "Lista de compra agregada.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context, "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//updateItem()

    //Buscar un registro por ID
    public Item findByID(int id, Context context){
        Item item = new Item();
        Cursor cursor = super.findByID(DB_TABLE,id,DB_ALL_COLUMNS);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            item.setID(cursor.getInt(0));
            ProductDBHelper prodDBH = new ProductDBHelper(context);
            item.setProduct(prodDBH.findByID(cursor.getInt(1),context));
            BuyListDBHelper blDBH = new BuyListDBHelper(context);
            item.setBuyList(blDBH.findByID(cursor.getInt(2),context));
            item.setQuantity(cursor.getFloat(3));
            item.setUnitPrice(cursor.getFloat(4));
            if(cursor.getInt(5) != 0){
                item.setIntoChart(true);
            } else{
                item.setIntoChart(false);
            }
        }
        return item;
    }//findByID()

    //Borrar un registro
    public void deleteItem(Item item, Context context){
        try{
            super.delete(DB_TABLE, "id=" + item.getID(), null);
            Toast.makeText(context, "Lista de compra borrada correctamente", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Error al borrar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }//deleteItem()

    public ArrayList<Item> getAll(Context context){
        ArrayList<Item> items = new ArrayList<>();

        Cursor cursor = super.findAll(DB_TABLE,DB_ALL_COLUMNS);
        while (cursor.moveToNext()){
            Item item = new Item();

            item.setID(cursor.getInt(0));
            ProductDBHelper prodDBH = new ProductDBHelper(context);
            item.setProduct(prodDBH.findByID(cursor.getInt(1),context));
            BuyListDBHelper blDBH = new BuyListDBHelper(context);
            item.setBuyList(blDBH.findByID(cursor.getInt(2),context));
            item.setQuantity(cursor.getFloat(3));
            item.setUnitPrice(cursor.getFloat(4));
            if(cursor.getInt(5) != 0){
                item.setIntoChart(true);
            } else{
                item.setIntoChart(false);
            }
            items.add(item);
        }
        return items;
    }//getAll()
}
