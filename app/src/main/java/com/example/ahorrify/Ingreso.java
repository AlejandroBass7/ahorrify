package com.example.ahorrify;

public class Ingreso {
    private String categoria;
    private double monto;

    public Ingreso(String categoria, double monto) {
        this.categoria = categoria;
        this.monto = monto;
    }

    public String getCategoria() { return categoria; }
    public double getMonto() { return monto; }
}