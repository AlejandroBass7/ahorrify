package com.example.ahorrify;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EnviarCorreo extends AppCompatActivity {
    TextView txtCorreo, txtAsunto, txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enviar_correo);
        txtCorreo = findViewById(R.id.edit_Correo);
        txtAsunto = findViewById(R.id.edit_Asunto);
        txtMensaje = findViewById(R.id.edit_Mensaje);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button enviar = findViewById(R.id.btnEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarCorreo(v);
                finish();
            }
        });
        Button cancelar = findViewById(R.id.btnCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelar = new Intent(EnviarCorreo.this, activity_perfil.class);
                startActivity(cancelar);
            }
        });
    }
    public void enviarCorreo(View v) {
        Intent inte = new Intent(Intent.ACTION_SENDTO);
        inte.setData(Uri.parse("mailto:"));
        inte.putExtra(Intent.EXTRA_EMAIL, new String[]{txtCorreo.getText().toString()});
        inte.putExtra(Intent.EXTRA_SUBJECT, txtAsunto.getText().toString());
        inte.putExtra(Intent.EXTRA_TEXT, txtMensaje.getText().toString());
        startActivity(inte);
    }
}