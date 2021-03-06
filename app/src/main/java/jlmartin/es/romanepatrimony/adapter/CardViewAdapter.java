package jlmartin.es.romanepatrimony.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jlmartin.es.romanepatrimony.R;
import jlmartin.es.romanepatrimony.domain.PatrimonioResumen;

public class CardViewAdapter extends RecyclerView.Adapter<PatrimonioViewHolder> {

    private List<PatrimonioResumen> patrimonios;

    public CardViewAdapter(List<PatrimonioResumen> patrimonios) {
        this.patrimonios = patrimonios;
    }

    @NonNull
    @Override
    public PatrimonioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_patrimonio, parent, false);
        return new PatrimonioViewHolder(itemView, patrimonios,parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull PatrimonioViewHolder holder, int position) {
        PatrimonioResumen patrimonio = patrimonios.get(position);
        holder.titulo.setText(patrimonio.getTitulo());
        if(patrimonio.getImagen()!=null) {
            Bitmap bitmap = BitmapFactory.decodeStream(patrimonio.getImagen());
            holder.imagen.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return patrimonios.size();
    }
}
