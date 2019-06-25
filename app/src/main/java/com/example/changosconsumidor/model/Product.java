package com.example.changosconsumidor.model;



import android.content.Context;
import android.widget.Toast;

import com.example.changosconsumidor.database.ProductDBHelper;

import java.util.ArrayList;


public class Product {
    private int id;
    private String mark;
    private String name;
    private float contentQuantity;
    private String contentUnit;
    private Category category;

//Metodos set y get

    public int getID() {
        return id;
    }

    public void setID(int id){
        this.id=id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark){
        this.mark=mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public float getContentQuantity() {
        return contentQuantity;
    }

    public void setContentQuantity(float contentQuantity){
        this.contentQuantity=contentQuantity;
    }

    public String getContentUnit() {
        return contentUnit;
    }

    public void setContentUnit(String contentUnit){
        this.contentUnit=contentUnit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category){
        this.category=category;
    }

// Metodos
    //createproduct
    public void crear(Context context){
        ProductDBHelper prodDBH = new ProductDBHelper(context);
        try{
            prodDBH.createProduct(this, context);
            Toast.makeText(context, "Producto creado", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            Toast.makeText(context, "Error al crear producto " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    //update
    public boolean modificar(Context context){
        ProductDBHelper prodDBH = new ProductDBHelper(context);
        try{
            prodDBH.updateProduct(this, context);
            Toast.makeText(context,"Producto modificado ",Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Error al crear producto"+e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //find id
    public Product buscar(Context context){
        ProductDBHelper prodDBH = new ProductDBHelper(context);
        Product product = new Product();
        try{
            prodDBH.findByID(id,context);//revisa!!!!!!!!
            Toast.makeText(context,"Producto encontrado",Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            Toast.makeText(context, "Producto no encontrado"+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        return product;
    }
    //deleteproduct
    public void borrarReg(Context context){
        ProductDBHelper prodDBH = new ProductDBHelper(context);
        try{
            prodDBH.deleteProduct(this,context);
            Toast.makeText(context,"Registro borrado",Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            Toast.makeText(context, "Registro no borrado"+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    //getall
    public ArrayList<Product> traerTodo(Context context){
        ProductDBHelper prodDBH = new ProductDBHelper(context);
        ArrayList<Product> products = new ArrayList<>();
        try{
            products=prodDBH.getAll(context);
            Toast.makeText(context,"Producto encontrado",Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(context, "Producto no encontrado"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return products;
    }

    }

