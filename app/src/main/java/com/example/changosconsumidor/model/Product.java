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
    public void modificar(Context context, Product product){
        ProductDBHelper prodDBH = new ProductDBHelper(context);
        try{
            prodDBH.updateProduct(product, context);
            Toast.makeText(context,"Producto modificado ",Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            Toast.makeText(context, "Error al crear producto"+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    //find id
    public Product buscar(Context context, int id){
        ProductDBHelper prodDBH = new ProductDBHelper(context);
        Product product = new Product();
        try{
            product = prodDBH.findByID(id,context);
            Toast.makeText(context,"Producto encontrado",Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(context, "Producto no encontrado"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return product;
    }
    //Borra un producto, pasando un producto con el ID
    public void borrarReg(Context context, Product product){
        ProductDBHelper prodDBH = new ProductDBHelper(context);
        try{
            prodDBH.deleteProduct(product,context);
            Toast.makeText(context,"Registro borrado",Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(context, "Registro no borrado"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //Retorna una ArrayList con todos los productos de una categoria
    public ArrayList<Product> traerProductsCategory(Context context, Category category){
        ProductDBHelper prodDBH = new ProductDBHelper(context);
        ArrayList<Product> productsDB = new ArrayList<>();
        try{
            productsDB = prodDBH.findByCategory(category, context);
            Toast.makeText(context,"Producto encontrado",Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(context, "Producto no encontrado", Toast.LENGTH_SHORT).show();
        }
        return productsDB;
    }

    }

