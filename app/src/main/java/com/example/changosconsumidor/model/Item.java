package com.example.changosconsumidor.model;

public class Item {
    private Product product;
    private BuyList buyList;
    private float quantity;
    private float unitPrice;
    private boolean intoChart;
    private int id;

    //metodos get y set

    public int getID() {
        return id;
    }

    public void setID(int id){
        this.id=id;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setIntoChart(boolean intoChart) {
        this.intoChart = intoChart;
    }

    public boolean isIntoChart() {
        return intoChart;
    }


    public float getUnitPrice(){
        return unitPrice;
    }

    public void setProduct(Product product){
        this.product=product;
    }

    public Product getProduct(){
        return product;
    }

    public BuyList getBuyList() {
        return buyList;
    }


    public void setBuyList(BuyList buyList){
        this.buyList=buyList;
    }

    public float getQuantity(){
        return quantity;
    }

    public void setQuantity(float quantity){
        this.quantity=quantity;
    }



}
