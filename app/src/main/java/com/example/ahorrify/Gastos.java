package com.example.ahorrify;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.health.connect.datatypes.ExerciseCompletionGoal;
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

public class Gastos extends AppCompatActivity {
    EditText etCategorias, etCantidad;
    adminSqliteOpenHelper admin;

    RecyclerView rv;
    GastoAdapter adapter;
    List<Gasto> resultados = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gastos);
        etCategorias = findViewById(R.id.txtCategorias);
        etCantidad = findViewById(R.id.txtCantidad);
        admin = new adminSqliteOpenHelper(this, "bdAhorrify", null, 1);
        // Inicializar RecyclerView una sola vez
        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Cargar los datos automáticamente al abrir
        ConsultarGastos(null);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button volverDashIng = findViewById(R.id.btnVolver);
        volverDashIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(Gastos.this, dashboard.class);
                startActivity(volver);
            }
        });
        ImageView irPerfil = findViewById(R.id.imgprofile);
        irPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(Gastos.this, activity_perfil.class);
                startActivity(perfil);
            }
        });
    }
    public void AgregarGasto(View v){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date hoy = new Date();
        String fechaActual = sdf.format(hoy);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("categoria", etCategorias.getText().toString());
        registro.put("monto", etCantidad.getText().toString());
        registro.put("fecha", fechaActual);
        bd.insert("gastos", null, registro);
        etCategorias.setText("");
        etCantidad.setText("");
        ConsultarGastos(null);
        Toast.makeText(this, "El ingreso se ha guardado correctamente", Toast.LENGTH_LONG).show();
        bd.close();
    }
    public void ConsultarGastos(View v){
        String categoriaBusqueda = etCategorias.getText().toString();
        SQLiteDatabase bd = admin.getReadableDatabase(); // Usar Readable para consultas
        resultados.clear();
        List<Gasto> resultados = new ArrayList<>();

        String query = categoriaBusqueda.isEmpty() ?
                "SELECT id, categoria, monto FROM gastos" :
                "SELECT id, categoria, monto FROM gastos WHERE categoria = '" + categoriaBusqueda + "'";

        Cursor fila = bd.rawQuery(query, null);
        adapter = new GastoAdapter(resultados);
        rv.setAdapter(adapter);
        if (fila.moveToFirst()) {
            do {
                // Agregamos cada registro a la lista
                resultados.add(new Gasto(
                        fila.getInt(0),
                        fila.getString(1), // categoria
                        fila.getDouble(2)  // monto
                ));
            } while (fila.moveToNext());

            // Configuramos el RecyclerView con los resultados
            RecyclerView rv = findViewById(R.id.recyclerView);
            rv.setLayoutManager(new LinearLayoutManager(this));
            GastoAdapter adapter = new GastoAdapter(resultados);
            rv.setAdapter(adapter);

            Toast.makeText(this, "Se encontraron " + resultados.size() + " registros", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }
    public void BorrarGasto(View v){
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Categoria = etCategorias.getText().toString();
        int cant = bd.delete("gastos", "categoria='"+Categoria+"'",null);
        if (cant==1){
            etCategorias.setText("");
            etCantidad.setText("");
            Toast.makeText(this, "El gasto fue eliminado", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "La categoria no existe", Toast.LENGTH_LONG).show();
        }
        ConsultarGastos(null);
        bd.close();
    }
    public void ModificarGasto(View v){
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Categoria = etCategorias.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("monto", etCantidad.getText().toString());
        int cant = bd.update("gastos", registro, "categoria='"+Categoria+"'",null);
        if (cant==1){
            etCategorias.setText("");
            etCantidad.setText("");
            Toast.makeText(this, "Se actualizo el ingreso.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "No se actualizo el ingreso", Toast.LENGTH_LONG).show();
        }
        ConsultarGastos(null);
        bd.close();
    }
}