package com.example.changosconsumidor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ListaActivity extends AppCompatActivity {

    private ListView listaItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaItems = (ListView) findViewById(R.id.listItems);

    }

    public void onClickAgregarItemLista(View view) {
        Intent intent = null;
        switch(view.getId()) {
            case R.id.btn_de_lista_a_home:
                intent = new Intent(ListaActivity.this, HomeActivity.class);
                break;
            case R.id.btnAgregarItem:
                intent = new Intent(ListaActivity.this, AgregarItem.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    public void llenarListaItems() {

    }
}
