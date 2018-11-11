package jlmartin.es.romanepatrimony;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jlmartin.es.romanepatrimony.adapter.CardViewAdapter;
import jlmartin.es.romanepatrimony.domain.PatrimonioDetallado;
import jlmartin.es.romanepatrimony.domain.PatrimonioPosicion;
import jlmartin.es.romanepatrimony.domain.PatrimonioResumen;
import jlmartin.es.romanepatrimony.sql.ContractSql;
import jlmartin.es.romanepatrimony.sql.RomaneDbHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    //CONSTANTES
    private static final String ACTUALIZADO = "actualizado";
    private static final LatLng CENTER = new LatLng(40, -4);
    //objetos de android
    private RecyclerView recyclerView;
    private CardViewAdapter cardViewAdapter;
    private SQLiteDatabase db;
    private LinearLayout linearLayout;
    //datos
    private List<PatrimonioResumen> patrimonios = new ArrayList<>();
    private List<PatrimonioPosicion> posiciones = new ArrayList<>();

    //firebase
    private DatabaseReference mDatabase;
    private ValueEventListener eventListener;
    private GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pintamos la vista principal
        linearLayout = findViewById(R.id.linearMain);
        View viewList = getLayoutInflater()
                .inflate(R.layout.activity_list, null, false);
        linearLayout.addView(viewList);

        View viewMap = getLayoutInflater()
                .inflate(R.layout.activity_maps, null, false);
        linearLayout.addView(viewMap);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        linearLayout.getChildAt(1).setVisibility(View.GONE);
        linearLayout.getChildAt(0).setVisibility(View.VISIBLE);

        //recuperamos valor de firebase
        mDatabase =
                FirebaseDatabase.getInstance().getReference();

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (!ACTUALIZADO.equals(dataSnapshot.child("version").getValue().toString())) {
                    ActualizadoDialogFragment dialog = new ActualizadoDialogFragment();
                    dialog.setPackageName(getPackageName());
                    dialog.show(getSupportFragmentManager(), "Alerta");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "Error!", databaseError.toException());
            }
        };

        mDatabase.addListenerForSingleValueEvent(eventListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //lanzamos base de datos sqlite
        RomaneDbHelper helper = new RomaneDbHelper(this);
        db = helper.getReadableDatabase();

        recyclerView = findViewById(R.id.patrimonios);
        cardViewAdapter = new CardViewAdapter(patrimonios);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardViewAdapter);
        rellenarDatosListado();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_inicio:
                linearLayout.getChildAt(1).setVisibility(View.GONE);
                linearLayout.getChildAt(0).setVisibility(View.VISIBLE);
                break;
            case R.id.nav_maps:
                linearLayout.getChildAt(1).setVisibility(View.VISIBLE);
                linearLayout.getChildAt(0).setVisibility(View.GONE);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void rellenarDatosListado() {
        //columnas que queremos sacar de sqlite
        String[] projectionPatrimonio = {
                BaseColumns._ID,
                ContractSql.Patrimonio.COLUMNA_DENOMINACION,
                ContractSql.Patrimonio.COLUMNA_OTRASDENOMINACIONES,
                ContractSql.Patrimonio.COLUMNA_CODTIPO,
                ContractSql.Patrimonio.COLUMNA_CODMUNICIPIO,
                ContractSql.Patrimonio.COLUMNA_DATOSHISTORICOS,
                ContractSql.Patrimonio.COLUMNA_DESCRIPCION};
        //ordenacion que queremos
        String sortOrder =
                ContractSql.Patrimonio.COLUMNA_DENOMINACION + " ASC";
        //preparamos la query
        Cursor cursor = db.query(
                ContractSql.Patrimonio.TABLA,
                projectionPatrimonio,
                null,
                null,
                null,
                null,
                sortOrder
        );
        AssetManager assetManager = getAssets();
        //recorremos la busqueda
        while (cursor.moveToNext()) {
            String denominacion = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractSql.Patrimonio.COLUMNA_DENOMINACION));
            String otraDenominacion = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractSql.Patrimonio.COLUMNA_OTRASDENOMINACIONES));
            String descripccion = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractSql.Patrimonio.COLUMNA_DESCRIPCION));
            String datosHistoricos = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractSql.Patrimonio.COLUMNA_DATOSHISTORICOS));
            int tipo = cursor.getInt(
                    cursor.getColumnIndexOrThrow(ContractSql.Patrimonio.COLUMNA_CODTIPO));
            int municipio = cursor.getInt(
                    cursor.getColumnIndexOrThrow(ContractSql.Patrimonio.COLUMNA_CODMUNICIPIO));

            String[] projectionTipo = {ContractSql.Tipo.COLUMNA_DESCRIPCION};
            String[] argsTipo = new String[]{String.valueOf(tipo)};
            Cursor cursorTipo = db.query(
                    ContractSql.Tipo.TABLA,
                    projectionTipo,
                    ContractSql.Tipo._ID + "=?",
                    argsTipo,
                    null,
                    null,
                    null
            );
            String tipoDes=null;
            while (cursorTipo.moveToNext()) {
                tipoDes = cursorTipo.getString(0);
            }

            String[] projectionMunicipio = {ContractSql.Municipio.COLUMNA_NOMBRE,ContractSql.Municipio.COLUMNA_LATITUD,ContractSql.Municipio.COLUMNA_LONGITUD};
            String[] argsMunic = new String[]{String.valueOf(municipio)};
            Cursor cursorMunic = db.query(
                    ContractSql.Municipio.TABLA,
                    projectionMunicipio,
                    ContractSql.Municipio._ID + "=?",
                    argsMunic,
                    null,
                    null,
                    null
            );
            String municDes=null;
            Double latitud=null;
            Double longitud=null;
            while (cursorMunic.moveToNext()) {
                municDes = cursorMunic.getString(0);
                latitud = cursorMunic.getDouble(1);
                longitud = cursorMunic.getDouble(2);
            }

            PatrimonioDetallado descripcionResumen = new PatrimonioDetallado(otraDenominacion, tipoDes, descripccion, datosHistoricos,municDes);

            InputStream imagen = null;
            try {
                imagen = assetManager.open(otraDenominacion + ".png");
            } catch (IOException e) {
                System.err.print("Error al buscar la imagen");
            }

            PatrimonioResumen patrimonioResumen = new PatrimonioResumen(denominacion, imagen);//imagen 300*100
            patrimonioResumen.setDescripcion(descripcionResumen);
            patrimonios.add(patrimonioResumen);

            posiciones.add(new PatrimonioPosicion(denominacion,latitud,longitud));
        }
        cursor.close();

        cardViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        for(PatrimonioPosicion posicion:posiciones){
            LatLng latLong = new LatLng(posicion.getLatitud(),posicion.getLongitud());
            map.addMarker(new MarkerOptions().position(latLong).title(posicion.getTitulo()));
        }

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(CENTER,5));
    }
}
