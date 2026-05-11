package com.example.ahorrify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IngresoAdapter extends RecyclerView.Adapter<IngresoAdapter.ViewHolder> {
    private List<Ingreso> listaIngresos;

    public IngresoAdapter(List<Ingreso> listaIngresos) {
        this.listaIngresos = listaIngresos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingreso, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingreso ingreso = listaIngresos.get(position);
        holder.tvCat.setText(ingreso.getCategoria());
        holder.tvMon.setText("$ " + ingreso.getMonto());
    }

    @Override
    public int getItemCount() { return listaIngresos.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCat, tvMon;
        public ViewHolder(View itemView) {
            super(itemView);
            tvCat = itemView.findViewById(R.id.tvItemCategoria);
            tvMon = itemView.findViewById(R.id.tvItemMonto);
        }
    }
}