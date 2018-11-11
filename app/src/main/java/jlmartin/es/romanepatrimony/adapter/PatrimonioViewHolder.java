package jlmartin.es.romanepatrimony.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jlmartin.es.romanepatrimony.DialogFragmentDetail;
import jlmartin.es.romanepatrimony.R;
import jlmartin.es.romanepatrimony.domain.PatrimonioResumen;

public class PatrimonioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView titulo;
    ImageView imagen;
    private Context context;
    private List<PatrimonioResumen> patrimonios;

    public PatrimonioViewHolder(View view, List<PatrimonioResumen> patrimonios,Context context) {
        super(view);
        this.patrimonios = patrimonios;
        this.context = context;
        CardView cardView = view.findViewById(R.id.cardview_patrimonio);
        cardView.setCardElevation(20);
        cardView.setRadius(15);
        cardView.setOnClickListener(this);

        titulo = view.findViewById(R.id.titulo);
        imagen = view.findViewById(R.id.imagen);
    }

    @Override
    public void onClick(View v) {

        DialogFragmentDetail dialogFragmentDetail = new DialogFragmentDetail();
        dialogFragmentDetail.setContext(context);
        dialogFragmentDetail.setPatrimonios(patrimonios);
        dialogFragmentDetail.setIndice(getAdapterPosition());
        dialogFragmentDetail.showDialog();


    }
}
