package com.example.shopar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class admincategoryactivity extends AppCompatActivity {

    private TextView shoes,furniture,toys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admincategoryactivity);

        shoes = findViewById(R.id.tv_adminshoescategory);
        furniture  = findViewById(R.id.tv_adminfurniturecategory);
        toys =  findViewById(R.id.tv_admintoyscategory);

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admincategoryactivity.this,adminActivity.class);
                intent.putExtra("category","shoes");
                startActivity(intent);
            }
        });
        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admincategoryactivity.this,adminActivity.class);
                intent.putExtra("category","furniture");
                startActivity(intent);
            }
        });
        toys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admincategoryactivity.this,adminActivity.class);
                intent.putExtra("category","toys");
                startActivity(intent);
            }
        });
    }
}