package jlmartin.es.romanepatrimony.sql;

import android.provider.BaseColumns;

public class ContractSql {
    public ContractSql(){

    }
    public static class Actividad implements BaseColumns {
        public static final String TABLA = "actividad";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
    }

    public static class PeriodoHistorico implements BaseColumns {
        public static final String TABLA = "periodo_historico";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
    }


    public static class Tipo implements BaseColumns {
        public static final String TABLA = "tipo";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
    }

    public static class Municipio implements BaseColumns {
        public static final String TABLA = "municipio";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_PROVINCIA_ID = "cod_provincia";
        public static final String COLUMNA_LATITUD = "latitud";
        public static final String COLUMNA_LONGITUD = "longitud";
    }

    public static class Patrimonio implements BaseColumns {
        public static final String TABLA = "patrimonio";
        public static final String COLUMNA_DENOMINACION = "denominacion";
        public static final String COLUMNA_OTRASDENOMINACIONES = "otras_denominaciones";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
        public static final String COLUMNA_BIBLIOGRAFIA = "bibliografia";
        public static final String COLUMNA_DATOSHISTORICOS = "datos_historicos";
        public static final String COLUMNA_CODTIPO = "cod_tipo";
        public static final String COLUMNA_CODMUNICIPIO = "cod_municipio";
        public static final String COLUMNA_CODPADRE = "cod_padre";
    }

    public static class TipologiaPatrimonio implements BaseColumns {
        public static final String TABLA = "tipologia_patrimonio";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
        public static final String COLUMNA_CRONOLOGIA = "cronologia";
        public static final String COLUMNA_CODPATRIMONIO = "cod_patrimonio";
        public static final String COLUMNA_CODPERIODOHISTORICO = "cod_periodo_historico";
        public static final String COLUMNA_CODACTIVIDAD = "cod_actividad";
    }
    //ACTIVIDAD
    public static final String SQL_CREATE_ACTIVIDAD =
            "CREATE TABLE " + ContractSql.Actividad.TABLA + " (" +
                    ContractSql.Actividad._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.Actividad.COLUMNA_DESCRIPCION + " TEXT)";

    public static final String SQL_DELETE_ACTIVIDAD =
            "DROP TABLE IF EXISTS " + ContractSql.Actividad.TABLA;

    //PERIODO HISTORICO
    public static final String SQL_CREATE_PERIODO_HISTORICO =
            "CREATE TABLE " + ContractSql.PeriodoHistorico.TABLA + " (" +
                    ContractSql.PeriodoHistorico._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.PeriodoHistorico.COLUMNA_DESCRIPCION + " TEXT)";

    public static final String SQL_DELETE_PERIODO_HISTORICO =
            "DROP TABLE IF EXISTS " + ContractSql.PeriodoHistorico.TABLA;

    //TIPO
    public static final String SQL_CREATE_TIPO =
            "CREATE TABLE " + ContractSql.Tipo.TABLA + " (" +
                    ContractSql.Tipo._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.Tipo.COLUMNA_DESCRIPCION + " TEXT)";

    public static final String SQL_DELETE_TIPO =
            "DROP TABLE IF EXISTS " + ContractSql.Tipo.TABLA;

    //MUNICIPIO
    public static final String SQL_CREATE_MUNICIPIO =
            "CREATE TABLE " + ContractSql.Municipio.TABLA + " (" +
                    ContractSql.Municipio._ID + " INTEGER PRIMARY KEY," +
                    ContractSql.Municipio.COLUMNA_NOMBRE + " TEXT," +
                    ContractSql.Municipio.COLUMNA_PROVINCIA_ID + " INTEGER," +
                    ContractSql.Municipio.COLUMNA_LATITUD + " REAL," +
                    ContractSql.Municipio.COLUMNA_LONGITUD + " REAL)";

    public static final String SQL_DELETE_MUNICIPIO =
            "DROP TABLE IF EXISTS " + ContractSql.Municipio.TABLA;

    //PATRIMONIO
    public static final String SQL_CREATE_PATRIMONIO =
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

    public static final String SQL_DELETE_PATRIMONIO =
            "DROP TABLE IF EXISTS " + ContractSql.Patrimonio.TABLA;

    //PATRIMONIO
    public static final String SQL_CREATE_TIPOLOGIA_PATRIMONIO =
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

    public static final String SQL_DELETE_TIPOLOGIA_PATRIMONIO =
            "DROP TABLE IF EXISTS " + ContractSql.Patrimonio.TABLA;

}
