package com.example.ahorrify;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class adminSqliteOpenHelper extends SQLiteOpenHelper {

    public adminSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table usuarios (id integer primary key autoincrement, nombre text, correo text unique, contrasena text, foto text)");
        db.execSQL("create table ingresos (id integer primary key autoincrement, categoria text, monto real, fecha text)");
        db.execSQL("create table gastos (id integer primary key autoincrement, categoria text, monto real, fecha text)");
        db.execSQL("create table metas (id integer primary key autoincrement, nombre text, monto_objetivo real, monto_actual real)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){

    }
}
