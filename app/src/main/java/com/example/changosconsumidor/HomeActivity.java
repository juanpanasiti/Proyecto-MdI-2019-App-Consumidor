package com.example.changosconsumidor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView listaCard, catCard, prodCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        listaCard = (CardView) findViewById(R.id.cvLista);
        catCard = (CardView) findViewById(R.id.cvCategorias);
        prodCard = (CardView) findViewById(R.id.cvProductos);

        listaCard.setOnClickListener(this);
        catCard.setOnClickListener(this);
        prodCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.cvLista:
                intent = new Intent(this, ListaActivity.class);
                break;
            case R.id.cvCategorias:
                intent = new Intent(this, CategoriasActivity.class);
                break;
            case R.id.cvProductos:
                intent = new Intent(this, ProductosActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    public void irCategorias(View v) {
        Intent intent = new Intent(this, CategoriasActivity.class);
        startActivity(intent);
    }
}
