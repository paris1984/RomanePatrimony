package jlmartin.es.romanepatrimony.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import jlmartin.es.romanepatrimony.R;
import jlmartin.es.romanepatrimony.entidad.Municipio;
import jlmartin.es.romanepatrimony.entidad.Tipo;

public class RomaneDbHelper extends SQLiteOpenHelper {

    //ACTIVIDAD
    private static final String SQL_CREATE_ACTIVIDAD =
            "CREATE TABLE " + ContractSql.Actividad.TABLA + " (" +
                    ContractSql.Actividad._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.Actividad.COLUMNA_DESCRIPCION + " TEXT)";

    private static final String SQL_DELETE_ACTIVIDAD =
            "DROP TABLE IF EXISTS " + ContractSql.Actividad.TABLA;

    //PERIODO HISTORICO
    private static final String SQL_CREATE_PERIODO_HISTORICO =
            "CREATE TABLE " + ContractSql.PeriodoHistorico.TABLA + " (" +
                    ContractSql.PeriodoHistorico._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.PeriodoHistorico.COLUMNA_DESCRIPCION + " TEXT)";

    private static final String SQL_DELETE_PERIODO_HISTORICO =
            "DROP TABLE IF EXISTS " + ContractSql.PeriodoHistorico.TABLA;

    //TIPO
    private static final String SQL_CREATE_TIPO =
            "CREATE TABLE " + ContractSql.Tipo.TABLA + " (" +
                    ContractSql.Tipo._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.Tipo.COLUMNA_DESCRIPCION + " TEXT)";

    private static final String SQL_DELETE_TIPO =
            "DROP TABLE IF EXISTS " + ContractSql.Tipo.TABLA;

    //MUNICIPIO
    private static final String SQL_CREATE_MUNICIPIO =
            "CREATE TABLE " + ContractSql.Municipio.TABLA + " (" +
                    ContractSql.Municipio._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.Municipio.COLUMNA_NOMBRE + " TEXT," +
                    ContractSql.Municipio.COLUMNA_PROVINCIA_ID + " INTEGER," +
                    ContractSql.Municipio.COLUMNA_LATITUD + " REAL," +
                    ContractSql.Municipio.COLUMNA_LONGITUD + " REAL)";

    private static final String SQL_DELETE_MUNICIPIO =
            "DROP TABLE IF EXISTS " + ContractSql.Municipio.TABLA;

    //PATRIMONIO
    private static final String SQL_CREATE_PATRIMONIO =
            "CREATE TABLE " + ContractSql.Patrimonio.TABLA + " (" +
                    ContractSql.Patrimonio._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.Patrimonio.COLUMNA_DENOMINACION + " TEXT," +
                    ContractSql.Patrimonio.COLUMNA_OTRASDENOMINACIONES + " TEXT," +
                    ContractSql.Patrimonio.COLUMNA_DESCRIPCION + " TEXT," +
                    ContractSql.Patrimonio.COLUMNA_BIBLIOGRAFIA + " TEXT," +
                    ContractSql.Patrimonio.COLUMNA_DATOSHISTORICOS + " TEXT," +
                    ContractSql.Patrimonio.COLUMNA_CODTIPO + " INTEGER," +
                    ContractSql.Patrimonio.COLUMNA_CODMUNICIPIO + " INTEGER," +
                    ContractSql.Patrimonio.COLUMNA_CODPADRE + " INTEGER," +
                    "FOREIGN KEY("+ContractSql.Patrimonio.COLUMNA_CODTIPO+") REFERENCES "+ContractSql.Tipo.TABLA+"("+ContractSql.Tipo._ID+")," +
                    "FOREIGN KEY("+ContractSql.Patrimonio.COLUMNA_CODMUNICIPIO+") REFERENCES "+ContractSql.Municipio.TABLA+"("+ContractSql.Municipio._ID+")," +
                    "FOREIGN KEY("+ContractSql.Patrimonio.COLUMNA_CODPADRE+") REFERENCES "+ContractSql.Patrimonio.TABLA+"("+ContractSql.Patrimonio._ID+")" +
                    ")";

    private static final String SQL_DELETE_PATRIMONIO =
            "DROP TABLE IF EXISTS " + ContractSql.Patrimonio.TABLA;

    //PATRIMONIO
    private static final String SQL_CREATE_TIPOLOGIA_PATRIMONIO =
            "CREATE TABLE " + ContractSql.TipologiaPatrimonio.TABLA + " (" +
                    ContractSql.TipologiaPatrimonio._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.TipologiaPatrimonio.COLUMNA_DESCRIPCION + " TEXT," +
                    ContractSql.TipologiaPatrimonio.COLUMNA_CRONOLOGIA + " TEXT," +
                    ContractSql.TipologiaPatrimonio.COLUMNA_CODPATRIMONIO + " TEXT," +
                    ContractSql.TipologiaPatrimonio.COLUMNA_CODPERIODOHISTORICO + " INTEGER," +
                    ContractSql.TipologiaPatrimonio.COLUMNA_CODACTIVIDAD + " INTEGER,"+
                    "FOREIGN KEY("+ContractSql.TipologiaPatrimonio.COLUMNA_CODPATRIMONIO+") REFERENCES "+ContractSql.Patrimonio.TABLA+"("+ContractSql.Patrimonio._ID+")," +
                    "FOREIGN KEY("+ContractSql.TipologiaPatrimonio.COLUMNA_CODPERIODOHISTORICO+") REFERENCES "+ContractSql.PeriodoHistorico.TABLA+"("+ContractSql.PeriodoHistorico._ID+")," +
                    "FOREIGN KEY("+ContractSql.TipologiaPatrimonio.COLUMNA_CODACTIVIDAD+") REFERENCES "+ContractSql.Actividad.TABLA+"("+ContractSql.Actividad._ID+")" +
                    ")";

            ;

    private static final String SQL_DELETE_TIPOLOGIA_PATRIMONIO =
            "DROP TABLE IF EXISTS " + ContractSql.Patrimonio.TABLA;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Romane.db";

    private final InputStream inputStreamMunicipios;
    private final InputStream inputStreamTipos;
    public RomaneDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        inputStreamMunicipios = context.getResources().openRawResource(R.raw.datas_municipios);
        inputStreamTipos = context.getResources().openRawResource(R.raw.datas_tipos);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACTIVIDAD);
        db.execSQL(SQL_CREATE_PERIODO_HISTORICO);
        db.execSQL(SQL_CREATE_TIPO);
        db.execSQL(SQL_CREATE_MUNICIPIO);
        db.execSQL(SQL_CREATE_PATRIMONIO);
        db.execSQL(SQL_CREATE_TIPOLOGIA_PATRIMONIO);

        //datos municipios
        List<Municipio> municipios = SqlCreateDbHelper.creationMunicipios(inputStreamMunicipios);
        int count=0;
        for (Municipio municipio:municipios) {
            ContentValues municipio1 = new ContentValues();
            municipio1.put(ContractSql.Municipio.COLUMNA_NOMBRE,municipio.getNombre());
            municipio1.put(ContractSql.Municipio.COLUMNA_PROVINCIA_ID,municipio.getProvincia_id());
            municipio1.put(ContractSql.Municipio.COLUMNA_LATITUD,municipio.getLatitud());
            municipio1.put(ContractSql.Municipio.COLUMNA_LONGITUD,municipio.getLongitud());
            if(municipio.setId(db.insert(ContractSql.Municipio.TABLA, null, municipio1))!=-1){
                count++;
            }else{
                System.err.println("Error al insertar:"+municipio);
            }
        }
        // datos tipos
        List<Tipo> tipos = SqlCreateDbHelper.creationTipos(inputStreamTipos);
        for(Tipo tipo:tipos){
            ContentValues tipos1 = new ContentValues();
            tipos1.put(ContractSql.Tipo.COLUMNA_DESCRIPCION, tipo.getDescripcion());
            long id1 = db.insert(ContractSql.Tipo.TABLA, null, tipos1);
            tipo.setId(id1);
        }

        // datos patrimonio
        ContentValues patrimonios1 = new ContentValues();
        patrimonios1.put(ContractSql.Patrimonio.COLUMNA_DENOMINACION," Zona Arqueológica de Granada");

        Optional<Tipo> tip = tipos.stream().filter(tipo -> tipo.getDescripcion().equals("Arqueológico")).findFirst();
        patrimonios1.put(ContractSql.Patrimonio.COLUMNA_CODTIPO,tip.orElse(new Tipo()).getId());

        Optional<Municipio> munic = municipios.stream().filter(municipio -> municipio.getNombre().equals("Granada")).findFirst();
        patrimonios1.put(ContractSql.Patrimonio.COLUMNA_CODMUNICIPIO,munic.orElse(new Municipio()).getId());

        patrimonios1.put(ContractSql.Patrimonio.COLUMNA_DESCRIPCION, "Este yacimiento comprende el conjunto arqueológico monumental del barrio del Albaycin: ibero-romano e islámico, el recinto histórico y monumental de la Alhambra y el Generalife, el alfar romano de Cartuja, la necrópolis romana de Los Vergeles, la necrópolis ibero-romana de la Calle San Antón, la necrópolis argárica en el recinto del Ferial, el Maristán y el baño almohade del Colegio de las Mercedarias. Se trata de un conjunto arqueológico y monumental cuyos datos más antiguos son de época argárica.");
        patrimonios1.put(ContractSql.Patrimonio.COLUMNA_OTRASDENOMINACIONES,"Granada");
        patrimonios1.put(ContractSql.Patrimonio.COLUMNA_BIBLIOGRAFIA,"Historia");

        db.insert(ContractSql.Patrimonio.TABLA, null, patrimonios1);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TIPOLOGIA_PATRIMONIO);
        db.execSQL(SQL_DELETE_PATRIMONIO);
        db.execSQL(SQL_DELETE_ACTIVIDAD);
        db.execSQL(SQL_DELETE_PERIODO_HISTORICO);
        db.execSQL(SQL_DELETE_TIPO);
        db.execSQL(SQL_DELETE_MUNICIPIO);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
