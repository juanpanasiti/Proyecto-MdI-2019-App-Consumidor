package com.example.changosconsumidor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {
    private ListView lvCategories;

    private String category_names[] = {};
    private String category_ids[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        lvCategories = findViewById(R.id.lvCategories);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_categories, category_names);
        lvCategories.setAdapter(adapter);

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CategoryActivity.this, "Seleccionado: " + lvCategories.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
