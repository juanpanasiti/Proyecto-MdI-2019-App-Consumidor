package com.example.changosconsumidor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NewCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
    }

    public void cancel(View v){
        this.finish();
    }//cancel()
    
    public void save(View v){
        Toast.makeText(this, "Todavia no guarda nada", Toast.LENGTH_SHORT).show();
    }
}
