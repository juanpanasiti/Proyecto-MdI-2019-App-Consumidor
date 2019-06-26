package com.example.changosconsumidor.model;

import java.util.ArrayList;
import android.content.Context;
import android.widget.Toast;


import com.example.changosconsumidor.database.CategoryDBHelper;

public class Category {
    private int id = 0;
    private String name;
    private Category father;
    private ArrayList<Product> products;


    public Category(String name){
        this.name = name;
    }

    public Category(){

    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getFatherID(){
        return 0;
    }
    public void setFather(Category father){
        this.father = father;
    }
    ///Consultas a la base de datos
    public static ArrayList<Category> all(){
        ArrayList<Category> categories = new ArrayList<>();
        return categories;
    }
    // Metodos

    public void crear(Context context) {
        CategoryDBHelper categoryDBHelper = new CategoryDBHelper(context);
        try {
            categoryDBHelper.createCategory(this, context);
            Toast.makeText(context, "Categoria Creada", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Error al crear Categoria" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }



    //createproduct
    public void modificar(Context context) {
        CategoryDBHelper categoryDBHelper = new CategoryDBHelper(context);
        try {
            categoryDBHelper.updateCategory(this, context);
            Toast.makeText(context, "Categoria Modificada", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Error al modificar Categoria" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    //updateBuy list
    public Category buscar(Context context) {
        CategoryDBHelper categoryDBHelper = new CategoryDBHelper(context);
        Category catego = new Category();
        try {
            catego = categoryDBHelper.findByID(id, context);
            Toast.makeText(context, "Categoria Encontrada", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "No se encontro la Categoria" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        return catego;
    }

    //findeByID
    public void borrarReg(Context context) {
        CategoryDBHelper categoryDBHelper = new CategoryDBHelper(context);
        try {
            categoryDBHelper.deleteCategory(this, context);
            Toast.makeText(context, "Categoria borrada correctamente", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Error al Borrar Categoria" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    //Retorna un arrayList con todas las categorias
    public ArrayList<Category> traerTodo(Context context) {
        CategoryDBHelper categoryDBHelper = new CategoryDBHelper(context);
        ArrayList<Category> category = new ArrayList<>();
        try {
            category = categoryDBHelper.getAll(context);
            Toast.makeText(context, "Lista de Categorias completa", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al Cargar Lista de Categorias" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return category;
    }

}


