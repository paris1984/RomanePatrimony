package jlmartin.es.romanepatrimony.sql;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jlmartin.es.romanepatrimony.R;
import jlmartin.es.romanepatrimony.entity.Municipio;
import jlmartin.es.romanepatrimony.entity.Patrimonio;
import jlmartin.es.romanepatrimony.entity.Tipo;

public class RomaneDbHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Romane.db";

    private final InputStream inputStreamMunicipios;
    private final InputStream inputStreamTipos;
    private final InputStream inputStreamPatrimonios;

    public RomaneDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        inputStreamMunicipios = context.getResources().openRawResource(R.raw.datas_municipios);
        inputStreamTipos = context.getResources().openRawResource(R.raw.datas_tipos);
        inputStreamPatrimonios = context.getResources().openRawResource(R.raw.datas_patrimonio);

    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContractSql.SQL_CREATE_TIPO);
        db.execSQL(ContractSql.SQL_CREATE_MUNICIPIO);
        db.execSQL(ContractSql.SQL_CREATE_PATRIMONIO);

        //datos municipios
        List<Municipio> municipios = CreateEntity.creationMunicipios(inputStreamMunicipios);
        for (Municipio municipio:municipios) {
            ContentValues municipio1 = new ContentValues();
            municipio1.put(ContractSql.Municipio.COLUMNA_NOMBRE,municipio.getNombre());
            municipio1.put(ContractSql.Municipio.COLUMNA_PROVINCIA_ID,municipio.getProvincia_id());
            municipio1.put(ContractSql.Municipio.COLUMNA_LATITUD,municipio.getLatitud());
            municipio1.put(ContractSql.Municipio.COLUMNA_LONGITUD,municipio.getLongitud());
            if(municipio.setId(db.insert(ContractSql.Municipio.TABLA, null, municipio1))==-1){
                System.err.println("Error al insertar:"+municipio);
            }
        }
        // datos tipos
        List<Tipo> tipos = CreateEntity.creationTipos(inputStreamTipos);
        for(Tipo tipo:tipos){
            ContentValues tipos1 = new ContentValues();
            tipos1.put(ContractSql.Tipo.COLUMNA_DESCRIPCION, tipo.getDescripcion());
            long id1 = db.insert(ContractSql.Tipo.TABLA, null, tipos1);
            tipo.setId(id1);
        }

        // datos patrimonio
        List<Patrimonio> patrimonios = CreateEntity.creationPatrimonios(inputStreamPatrimonios);

        for (Patrimonio patrimonio:patrimonios) {
            ContentValues patrimonios1 = new ContentValues();
            patrimonios1.put(ContractSql.Patrimonio.COLUMNA_DENOMINACION,patrimonio.getDenominacion());
            Optional<Tipo> tip = tipos.stream().filter(tipo -> tipo.getDescripcion().equals(patrimonio.getTipo())).findFirst();
            long tipoNum = tip.orElse(new Tipo()).getId();
            patrimonios1.put(ContractSql.Patrimonio.COLUMNA_CODTIPO, tipoNum);
            Optional<Municipio> munic = municipios.stream().filter(municipio -> municipio.getNombre().equals(patrimonio.getMunicipio())).findFirst();
            int municipioNum = munic.orElse(new Municipio()).getId();
            patrimonios1.put(ContractSql.Patrimonio.COLUMNA_CODMUNICIPIO, municipioNum);
            patrimonios1.put(ContractSql.Patrimonio.COLUMNA_DESCRIPCION, patrimonio.getDescripcion());
            patrimonios1.put(ContractSql.Patrimonio.COLUMNA_OTRASDENOMINACIONES,patrimonio.getOtraDenominacion());
            patrimonios1.put(ContractSql.Patrimonio.COLUMNA_DATOSHISTORICOS,patrimonio.getDatosHistoricos());
            db.insert(ContractSql.Patrimonio.TABLA, null, patrimonios1);

            patrimonio.setMunicipioNum(municipioNum);
            patrimonio.setTipoNum(tipoNum);
        }
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ContractSql.SQL_DELETE_PATRIMONIO);
        db.execSQL(ContractSql.SQL_DELETE_TIPO);
        db.execSQL(ContractSql.SQL_DELETE_MUNICIPIO);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
