package com.example.changosconsumidor.model;

import android.content.Context;
import android.widget.Toast;

import com.example.changosconsumidor.database.BuyListDBHelper;


import java.text.ParseException;
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

    public void setID(int id) {
        this.id = id;
    }

    public ArrayList getItems() {

        return items;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

//metodo recive string y lo pasa a un date

    public Date setDBDate(String date) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(date);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }


    public void setDate(Date date) {

        this.date = date;
    }
    //resive un date y debuelve un string

    public String getDBDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(new Date());
        return fecha;
    }

    // Metodos

    public void crear(Context context) {
        BuyListDBHelper buyListDBH = new BuyListDBHelper(context);
        try {
            buyListDBH.createBuyList(this, context);
            Toast.makeText(context, "Lista de Compras Creada", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Error al crear Lista de Compras" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    //createproduct
    public void modificar(Context context) {
        BuyListDBHelper buyListDBH = new BuyListDBHelper(context);
        try {
            buyListDBH.updateBuyList(this, context);
            Toast.makeText(context, "Lista de Compras Modificada", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Error al Modificar Lista de Compras" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    //updateBuy list
    public BuyList buscar(Context context) {
        BuyListDBHelper buyListDBH = new BuyListDBHelper(context);
        BuyList buyList = new BuyList();
        try {
            buyList = buyListDBH.findByID(id, context);
            Toast.makeText(context, "Lista Encontrada", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "No se encontro la lista" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        return buyList;
    }

    //findeByID
    public void borrarReg(Context context) {
        BuyListDBHelper buyListDBH = new BuyListDBHelper(context);
        try {
            buyListDBH.deleteBuyList(this, context);
            Toast.makeText(context, "Lista de compra borrada correctamente", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Error al Borrar Lista de Compras" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    //deleteBuyList

    public ArrayList<BuyList> traertodo(Context context, BuyList bl) {
        BuyListDBHelper buyListDBH = new BuyListDBHelper(context);
        ArrayList<BuyList> buyLists = new ArrayList<>();
        ArrayList<BuyList> buyLi = new ArrayList<>();
        try {
            buyLists = buyListDBH.getAll(context);
            for (int i = 0; i < buyLists.size(); i++) {
                if (buyLists.get(i).getID() == bl.getID()) {
                    buyLi.add(buyLists.get(i));


                }
            }
            buyListDBH.getAll(context);
            Toast.makeText(context, "Lista de compra Completa", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Error al Cargar Lista de Compras" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        return buyLi;
    }

}
    //Getall


