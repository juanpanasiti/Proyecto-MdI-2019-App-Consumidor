package com.example.changosconsumidor.model;

import java.util.ArrayList;

public class Category {
    private int id;
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
}
