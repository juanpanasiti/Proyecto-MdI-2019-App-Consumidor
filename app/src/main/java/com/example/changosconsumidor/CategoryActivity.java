package com.example.changosconsumidor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.changosconsumidor.database.CategoryDBHelper;

public class CategoryActivity extends AppCompatActivity {
    TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        tvTest = findViewById(R.id.tvTest);
        tvTest.setText("Registros: " + CategoryDBHelper.countCategories(this));

    }//onCreate()

    public void volver(View v){
        this.finish();
    }
}
