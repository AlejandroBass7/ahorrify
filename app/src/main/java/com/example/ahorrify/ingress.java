package com.example.ahorrify;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ingress extends AppCompatActivity {
    EditText etCategorias, etCantidad;
    adminSqliteOpenHelper admin;

    RecyclerView rv;
    IngresoAdapter adapter;
    List<Ingreso> resultados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingresos);
        etCategorias = findViewById(R.id.txtCategorias);
        etCantidad = findViewById(R.id.txtCantidad);
        admin = new adminSqliteOpenHelper(this, "bdAhorrify", null, 1);

        // Inicializar RecyclerView una sola vez
        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Cargar los datos automáticamente al abrir
        consultarCategoria(null);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button volverDashIng = findViewById(R.id.btnVolver);
        volverDashIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(ingress.this, dashboard.class);
                startActivity(volver);
            }
        });
        ImageView irPerfil = findViewById(R.id.imgprofile);
        irPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(ingress.this, activity_perfil.class);
                startActivity(perfil);
            }
        });
    }
    public void agregar(View v){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date hoy = new Date();
        String fechaActual = sdf.format(hoy);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("categoria", etCategorias.getText().toString());
        registro.put("monto", etCantidad.getText().toString());
        registro.put("fecha", fechaActual);
        bd.insert("ingresos", null, registro);
        etCategorias.setText("");
        etCantidad.setText("");
        consultarCategoria(null);
        Toast.makeText(this, "El ingreso se ha guardado correctamente", Toast.LENGTH_LONG).show();
        bd.close();
    }
    public void consultarCategoria(View v) {
        String categoriaBusqueda = etCategorias.getText().toString();
        SQLiteDatabase bd = admin.getReadableDatabase(); // Usar Readable para consultas
        resultados.clear();
        // Lista para guardar los resultados
        List<Ingreso> resultados = new ArrayList<>();

        // Consulta (si el campo está vacío, trae todos)
        String query = categoriaBusqueda.isEmpty() ?
                "SELECT categoria, monto FROM ingresos" :
                "SELECT categoria, monto FROM ingresos WHERE categoria = '" + categoriaBusqueda + "'";

        Cursor fila = bd.rawQuery(query, null);
        adapter = new IngresoAdapter(resultados);
        rv.setAdapter(adapter);
        if (fila.moveToFirst()) {
            do {
                // Agregamos cada registro a la lista
                resultados.add(new Ingreso(
                        fila.getString(0), // categoria
                        fila.getDouble(1)  // monto
                ));
            } while (fila.moveToNext());

            // Configuramos el RecyclerView con los resultados
            RecyclerView rv = findViewById(R.id.recyclerView);
            rv.setLayoutManager(new LinearLayoutManager(this));
            IngresoAdapter adapter = new IngresoAdapter(resultados);
            rv.setAdapter(adapter);

            Toast.makeText(this, "Se encontraron " + resultados.size() + " registros", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }
    public void BorrarIngreso(View v){
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Categoria = etCategorias.getText().toString();
        int cant = bd.delete("ingresos", "categoria='"+Categoria+"'",null);
        if (cant==1){
            etCategorias.setText("");
            etCantidad.setText("");
            Toast.makeText(this, "La categoria fue eliminada", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "La categoria no existe", Toast.LENGTH_LONG).show();
        }
        consultarCategoria(null);
        bd.close();
    }
    public void ModificarIngreso(View v){
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Categoria = etCategorias.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("monto", etCantidad.getText().toString());
        int cant = bd.update("ingresos", registro,"categoria='"+Categoria+"'",null);
        if (cant==1){
            etCategorias.setText("");
            etCantidad.setText("");
            Toast.makeText(this, "Se actualizo el ingreso.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "No se actualizo el ingreso", Toast.LENGTH_LONG).show();
        }
        consultarCategoria(null);
        bd.close();
    }
}