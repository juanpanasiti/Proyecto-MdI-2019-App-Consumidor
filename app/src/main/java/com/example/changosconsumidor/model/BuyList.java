package com.example.changosconsumidor.model;

import android.content.Context;
import android.widget.Toast;

import com.example.changosconsumidor.database.BuyListDBHelper;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BuyList {
    private ArrayList<Item> items;
    private String name;
    private Date date;
    private int id;

    //metodos set y get
    public int getID() {
        return id;
    }

    public void setID(int id){
        this.id=id;
    }

    public ArrayList getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public void setName(){

            this.name=name;

    }
    public void setDate(Date date){

        this.date=date;
    }
    public String getDBDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(new Date());
        return fecha;
    }

    // Metodos

    public boolean crear(Context context){
        BuyListDBHelper buyListDBH = new BuyListDBHelper(context);
        try{
            buyListDBH.createBuyList(this, context);
            Toast.makeText(context, "Lista de Compras Creada", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Error al crear Lista de Compras" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //createproduct
    public boolean modificar(Context context){
        BuyListDBHelper buyListDBH = new BuyListDBHelper(context);
        try{
            buyListDBH.updateBuyList(this, context);
            Toast.makeText(context, "Lista de Compras Modificada", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Error al Modificar Lista de Compras" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //updateBuy list
    public boolean buscar(Context context){
        BuyListDBHelper buyListDBH = new BuyListDBHelper(context);
        try{
            buyListDBH.findByID(id, context);
            Toast.makeText(context, "Lista Encontrada", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "No se encontro la lista" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //findeByID
    public boolean borrarReg(Context context){
        BuyListDBHelper buyListDBH = new BuyListDBHelper(context);
        try{
            buyListDBH.deleteBuyList(this, context);
            Toast.makeText(context, "Lista de compra borrada correctamente", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Error al Borrar Lista de Compras" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //deleteBuyList
    public boolean traertodo(Context context){
        BuyListDBHelper buyListDBH = new BuyListDBHelper(context);
        try{
            buyListDBH.getAll(context);
            Toast.makeText(context, "Lista de compra Completa", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Error al Cargar Lista de Compras" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //Getall

}
