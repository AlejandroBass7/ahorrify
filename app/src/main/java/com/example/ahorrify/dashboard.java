package com.example.ahorrify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button irIngresos = findViewById(R.id.btnIngresos);
        irIngresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresos = new Intent(dashboard.this, ingress.class);
                startActivity(ingresos);
            }
        });
        Button irGastos = findViewById(R.id.btnGastos);
        irGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gastos = new Intent(dashboard.this, Gastos.class);
                startActivity(gastos);
            }
        });
        Button irMetas= findViewById(R.id.btnMetas);
        irMetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent metas = new Intent(dashboard.this, activity_metas.class);
                startActivity(metas);
            }
        });
        ImageView irPerfil = findViewById(R.id.imgprofile);
        irPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(dashboard.this, activity_perfil.class);
                startActivity(perfil);
            }
        });
    }
}
