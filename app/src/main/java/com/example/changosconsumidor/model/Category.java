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
    //Crear categoria pasandole el contexto y la categoria con su nombre
    public void crear(Context context, Category cat) {
        CategoryDBHelper categoryDBHelper = new CategoryDBHelper(context);
        try {
            categoryDBHelper.createCategory(cat, context);
            Toast.makeText(context, "Categoria creada", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al crear Categoria" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //Modificar categoria pasandole el contexto y la categoria con su ID, obtenido de la base de datos
    public void modificar(Context context, Category cat) {
        CategoryDBHelper categoryDBHelper = new CategoryDBHelper(context);
        try {
            categoryDBHelper.updateCategory(cat, context);
            Toast.makeText(context, "Categoria modificada", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al modificar Categoria" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //Buscar Categoria en la base de datos por su ID
    public Category buscar(Context context, int id) {
        CategoryDBHelper categoryDBHelper = new CategoryDBHelper(context);
        Category categoryEncontrada = new Category();
        try {
            categoryEncontrada = categoryDBHelper.findByID(id, context);
            Toast.makeText(context, "Categoria encontrada", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "No existe la Categoria" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return categoryEncontrada;
    }

    //Borrar Categoria por su id
    public void borrarReg(Context context, Category cat) {
        CategoryDBHelper categoryDBHelper = new CategoryDBHelper(context);
        categoryDBHelper.deleteCategory(cat, context);
    }

    //Retorna un arrayList con todas las categorias
    public ArrayList<Category> traerTodo(Context context) {
        CategoryDBHelper categoryDBHelper = new CategoryDBHelper(context);
        ArrayList<Category> categoriasEncontradas = new ArrayList<>();
        try {
            categoriasEncontradas = categoryDBHelper.getAll(context);
            Toast.makeText(context, "Lista de Categorias completa", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al Cargar Lista de Categorias" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return categoriasEncontradas;
    }

}


