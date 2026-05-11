package com.example.ahorrify;

public class Ingreso {
    private int id; // Nuevo campo
    private String categoria;
    private double monto;

    public Ingreso(int id, String categoria, double monto) {
        this.id = id;
        this.categoria = categoria;
        this.monto = monto;
    }

    public int getId() { return id; }
    public String getCategoria() { return categoria; }
    public double getMonto() { return monto; }
}