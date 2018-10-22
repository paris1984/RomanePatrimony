package jlmartin.es.romanepatrimony;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jlmartin.es.romanepatrimony.adapter.CardViewAdapter;
import jlmartin.es.romanepatrimony.entidad.PatrimonioDescripcionResumen;
import jlmartin.es.romanepatrimony.entidad.PatrimonioResumen;
import jlmartin.es.romanepatrimony.sql.ContractSql;
import jlmartin.es.romanepatrimony.sql.RomaneDbHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private CardViewAdapter cardViewAdapter;
    private List<PatrimonioResumen> patrimonios = new ArrayList<>();
    private SQLiteDatabase db;

    //objetos del detalle
    private TextView titulo;
    private TextView otraDen;
    private TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RomaneDbHelper helper = new RomaneDbHelper(this);
        db = helper.getReadableDatabase();


        recyclerView = findViewById(R.id.patrimonios);
        cardViewAdapter = new CardViewAdapter(patrimonios);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardViewAdapter);
        rellenarDatos();
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

        if (id == R.id.nav_inicio) {
            // Handle the camera action
        } else if (id == R.id.nav_config) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void rellenarDatos() {

        String[] projection = {
                BaseColumns._ID,
                ContractSql.Patrimonio.COLUMNA_DENOMINACION,
                ContractSql.Patrimonio.COLUMNA_OTRASDENOMINACIONES,
                ContractSql.Patrimonio.COLUMNA_CODTIPO,
                ContractSql.Patrimonio.COLUMNA_CODPADRE,
                ContractSql.Patrimonio.COLUMNA_DESCRIPCION};


// How you want the results sorted in the resulting Cursor
        String sortOrder =
                ContractSql.Patrimonio.COLUMNA_DENOMINACION + " DESC";

        Cursor cursor = db.query(
                ContractSql.Patrimonio.TABLA,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        while (cursor.moveToNext()) {
            String denominacion = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractSql.Patrimonio.COLUMNA_DENOMINACION));
            String otraDenominacion = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractSql.Patrimonio.COLUMNA_OTRASDENOMINACIONES));
            String descripccion = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContractSql.Patrimonio.COLUMNA_DESCRIPCION));


            PatrimonioDescripcionResumen descripcionResumen = new PatrimonioDescripcionResumen(otraDenominacion,null,null,descripccion);
            PatrimonioResumen patrimonioResumen = new PatrimonioResumen(1000, denominacion, R.drawable.complutum);//imagen 300*100
            patrimonioResumen.setDescripcion(descripcionResumen);
            patrimonios.add(patrimonioResumen);
        }
        cursor.close();


        cardViewAdapter.notifyDataSetChanged();
    }
}
