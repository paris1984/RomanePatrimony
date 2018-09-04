package jlmartin.es.romanepatrimony;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jlmartin.es.romanepatrimony.entity.PatrimonioView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<PatrimonioView> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo;
        TextView descripcion;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.titulo = itemView.findViewById(R.id.textViewName);
            this.descripcion =itemView.findViewById(R.id.textViewVersion);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public CustomAdapter(ArrayList<PatrimonioView> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewTitulo = holder.titulo;
        TextView textViewDescripcion = holder.descripcion;
        ImageView imageView = holder.imageViewIcon;

        textViewTitulo.setText(dataSet.get(listPosition).getTitulo());
        textViewDescripcion.setText(dataSet.get(listPosition).getDescripcion());
        imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

