package jlmartin.es.romanepatrimony;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.util.List;

import jlmartin.es.romanepatrimony.entidad.PatrimonioResumen;

public class DialogFragmentDetail extends DialogFragment {
    private Activity context;
    private List<PatrimonioResumen> patrimonios;
    private int indice;

    private TextView titulo;
    private TextView otraDen;
    private TextView descripcion;

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
        titulo =  view.findViewById(R.id.titulo_tv);
        otraDen =  view.findViewById(R.id.otradenominacion_tv);
        descripcion =  view.findViewById(R.id.descripcion_tv);

        //le ponemos los valores necearios
        titulo.setText(patrimonios.get(indice).getTitulo());
        otraDen.setText(patrimonios.get(indice).getDescripcion().getOtraDenominacion());
        descripcion.setText(patrimonios.get(indice).getDescripcion().getDescripcion());
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
}
