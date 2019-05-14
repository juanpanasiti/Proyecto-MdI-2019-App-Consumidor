package com.example.changosconsumidor.modelo;

import java.util.ArrayList;

public class Category {
    private String name;
    private Category father;
    private ArrayList<Product> products;

    public Category(String name){
        this.name = name;
    }
    public Category(){

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

    ///Consultas a la base de datos
    public static ArrayList<Category> all(){
        ArrayList<Category> categories = new ArrayList<>();
        return categories;
    }
}
