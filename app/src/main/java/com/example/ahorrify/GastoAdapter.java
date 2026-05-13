package com.example.ahorrify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GastoAdapter extends RecyclerView.Adapter<GastoAdapter.ViewHolder> {
    private List<Gasto> listaGastos;

    public GastoAdapter(List<Gasto> listaGastos) {
        this.listaGastos = listaGastos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gasto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Gasto gasto = listaGastos.get(position);
        holder.tvCat.setText(gasto.getCategoria());
        holder.tvMon.setText("$ " + gasto.getMonto());
    }

    @Override
    public int getItemCount() { return listaGastos.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCat, tvMon;
        ImageButton btnDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            tvCat = itemView.findViewById(R.id.tvItemCategoriaGasto);
            tvMon = itemView.findViewById(R.id.tvItemMontoGasto);
        }
    }
}