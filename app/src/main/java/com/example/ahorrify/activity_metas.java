package com.example.ahorrify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_metas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_metas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button volver = findViewById(R.id.btnVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboard = new Intent(activity_metas.this, dashboard.class);
                startActivity(dashboard);
            }
        });
        Button tools = findViewById(R.id.btnTools);
        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirWeb(v);
            }
        });
    }
    public void abrirWeb(View v){
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://finanzasplus.co/herramientas"));
        startActivity(i);
    }
}