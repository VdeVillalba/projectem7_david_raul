package cat.villalba.projectem7_david_raul;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import java.util.ArrayList;
import java.util.Locale;


public class pantalla_principal extends BaseActivity {


    private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView mRecyclerView;
    private ArrayList<Peli> mPeliculas;
    private adaptadorPelis mAdapter;
    private Locale myLocale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

   /* private void initializeData() {

        String[] listaPelis = getResources().getStringArray(R.array.pelis_titulos);
        String[] listaInfo = getResources().getStringArray(R.array.pelis_info);
        String[] listaDirectores = getResources().getStringArray(R.array.pelis_director);
        String[] listaResumenes = getResources().getStringArray(R.array.pelis_resumen);
        TypedArray pelisRecursosImagenes =
                getResources().obtainTypedArray(R.array.pelis_images);

        mPeliculas.clear();

        for (int i = 0; i < listaPelis.length; i++) {
            mPeliculas.add(new Peli(listaPelis[i], listaInfo[i],pelisRecursosImagenes.getResourceId(i,0),listaResumenes[i],listaDirectores[i]));
        }

        pelisRecursosImagenes.recycle();

        mAdapter.notifyDataSetChanged();


    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantalla_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle options menu item clicks here.
        switch (item.getItemId()) {
            case R.id.catala:
                setNewLocale(this,LocaleManager.CATALAN);
                return true;

            case R.id.espanyol:
                setNewLocale(this,LocaleManager.SPANISH);
                return true;

            case R.id.angles:
                setNewLocale(this,LocaleManager.ENGLISH);
                return true;

            default:
                // Do nothing
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }

    private void setNewLocale(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(this, language);
        Intent intent = mContext.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
    /*public void resetPelis(View view) {
        initializeData();
    }*/
}
