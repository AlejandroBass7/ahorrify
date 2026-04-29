package com.example.ahorrify;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_perfil extends AppCompatActivity {
    final int CAPTURA_IMAGEN = 1;
    ImageView imgPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        imgPerfil = findViewById(R.id.foto_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button volverDashIng = findViewById(R.id.btnVolver);
        volverDashIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(activity_perfil.this, dashboard.class);
                startActivity(volver);
            }
        });
        Button abrirCamara = findViewById(R.id.btn_Foto);
        abrirCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto(v);
            }
        });
        Button enviarCorreo = findViewById(R.id.btnEnviarCorreo);
        enviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enviar = new Intent(activity_perfil.this, EnviarCorreo.class);
                startActivity(enviar);
            }
        });
    }
    public void tomarFoto (View v) {
        Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(in, CAPTURA_IMAGEN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURA_IMAGEN && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap bit1=(Bitmap)extras.get("data");
            imgPerfil.setImageBitmap(bit1);
        }
    }
}