package com.example.changosconsumidor.model;

import android.content.Context;
import android.widget.Toast;

import com.example.changosconsumidor.database.ProductDBHelper;

public class Product {
    private int id;
    private String mark;
    private String name;
    private float contentQuantity;
    private String contentUnit;
    private Category category;

    public int getID() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public String getName() {
        return name;
    }

    public float getContentQuantity() {
        return contentQuantity;
    }

    public String getContentUnit() {
        return contentUnit;
    }

    public Category getCategory() {
        return category;
    }

    public boolean crear(Context context){
        ProductDBHelper prodDBH = new ProductDBHelper();
        try{
            prodDBH.createProduct(this, context);
            Toast.makeText(context, "Producto creado", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Error al crear producto " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public boolean modificar(Context context){
        ProductDBHelper prodDBH = new ProductDBHelper();
        try{
            prodDBH.updateProduct(this, context);
            Toast.makeText(context,"Producto modificado ",Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Error al crear producto "+e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
