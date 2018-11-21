package jlmartin.es.romanepatrimony;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import jlmartin.es.romanepatrimony.domain.PatrimonioResumen;

public class DialogFragmentDetail extends DialogFragment implements View.OnClickListener {
    private Activity context;
    private List<PatrimonioResumen> patrimonios;
    private int indice;

    private TextView titulo;
    private TextView otraDen;
    private TextView descripcion;
    private TextView datosHistoricos;
    private TextView tipo;
    private TextView municipio;
    private Button cerrar;

    public void setContext(Context context) {
        this.context = (Activity)context;
    }

    public void setPatrimonios(List<PatrimonioResumen> patrimonios) {
        this.patrimonios = patrimonios;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        View view = inflater.inflate(R.layout.detail_patrimony, container, false);

        //recuperamos los objetos a rellenar de la vista
        titulo =  view.findViewById(R.id.titulo);
        otraDen =  view.findViewById(R.id.otradenominacion);
        descripcion =  view.findViewById(R.id.descripcion);
        datosHistoricos =  view.findViewById(R.id.datos);
        tipo = view.findViewById(R.id.tipo);
        municipio = view.findViewById(R.id.municipio);
        cerrar = view.findViewById(R.id.cerrar);
        cerrar.setOnClickListener(this);

        //le ponemos los valores necearios
        titulo.setText(patrimonios.get(indice).getTitulo());
        otraDen.setText(patrimonios.get(indice).getDescripcion().getOtraDenominacion());
        descripcion.setText(patrimonios.get(indice).getDescripcion().getDescripcion());
        datosHistoricos.setText(patrimonios.get(indice).getDescripcion().getDatosHistoricos());
        tipo.setText(patrimonios.get(indice).getDescripcion().getTipo());
        municipio.setText(patrimonios.get(indice).getDescripcion().getMunicipio());

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void showDialog() {

        FragmentManager fragmentManager = context.getFragmentManager();
        this.show(fragmentManager, "dialog");

    }

    @Override
    public void onClick(View view) {
        this.dismiss();
    }
}
