package com.example.changosconsumidor.model;

import android.content.Context;
import android.widget.Toast;

import com.example.changosconsumidor.database.ItemDBHelper;
import com.example.changosconsumidor.database.ProductDBHelper;

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

//metodos crear modificar borrar y buscar

    public boolean crear(Context context){
        ItemDBHelper ItemDBH = new ItemDBHelper(context);
        try{
            ItemDBH.createItem(this, context);
            Toast.makeText(context, "Item creado correctamente", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Error al crear Item " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    //update
    public boolean modificar(Context context){
        ItemDBHelper ItemDBH = new ItemDBHelper(context);
        try{
            ItemDBH.updateItem(this, context);
            Toast.makeText(context,"Item modificado ",Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Error al modificar"+e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //find id
    public boolean Buscar(Context context){
        ItemDBHelper ItemDBH = new ItemDBHelper(context);
        try{
            ItemDBH.findByID(id,context);//revisa!!!!!!!!
            Toast.makeText(context,"Item encontrado",Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Item no encontrado"+e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //deleteproduct
    public boolean BorrarReg(Context context){
        ItemDBHelper ItemDBH = new ItemDBHelper(context);
        try{
            ItemDBH.deleteItem(this,context);
            Toast.makeText(context,"Item borrado",Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Item no borrado"+e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //getall
    public boolean traertodo(Context context){
        ItemDBHelper ItemDBH = new ItemDBHelper(context);
        try{
            ItemDBH.getAll(context);
            Toast.makeText(context,"Items cargados",Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e){
            Toast.makeText(context, "Items no cargados"+e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
