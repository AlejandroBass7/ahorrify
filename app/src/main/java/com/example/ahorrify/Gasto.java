package com.example.ahorrify;

public class Gasto {
    private int id;
    private String categoria;
    private double monto;

    public Gasto(int id, String categoria, double monto) {
        this.id = id;
        this.categoria = categoria;
        this.monto = monto;
    }

    public int getId() { return id; }
    public String getCategoria() { return categoria; }
    public double getMonto() { return monto; }
}