package cat.villalba.projectem7_david_raul.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.view.MenuItem;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import java.util.Locale;

import cat.villalba.projectem7_david_raul.R;


public class pantalla_principal extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseUser firebaseUser;
    private NavigationView navigation;
    private TextView usuari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        setContentView(R.layout.activity_pantalla_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d = new Intent(getApplicationContext(), Chat.class);
                startActivity(d);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView usuari = headerView.findViewById(R.id.drawer_usuari);
        usuari.setText(firebaseUser.getEmail());

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_totespelis, R.id.nav_social, R.id.nav_usuario, R.id.nav_amigos,
                R.id.nav_logout, R.id.nav_llicencia)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantalla_principal, menu);

        return true;
    }

    public void logout(MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        Intent d = new Intent(getApplicationContext(), Login.class);
        startActivity(d);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle options menu item clicks here.
        Intent intent;
        switch (item.getItemId()) {
            case R.id.catala:
                Locale localizacion = new Locale("ca", "CAT");

                Locale.setDefault(localizacion);
                Configuration config = new Configuration();
                config.locale = localizacion;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                intent = getIntent();
                finish();
                startActivity(intent);
                return true;

            case R.id.espanyol:
                Locale localizacion2 = new Locale("es", "SPA");

                Locale.setDefault(localizacion2);
                Configuration config2 = new Configuration();
                config2.locale = localizacion2;
                getBaseContext().getResources().updateConfiguration(config2, getBaseContext().getResources().getDisplayMetrics());
                intent = getIntent();
                finish();
                startActivity(intent);
                return true;

            case R.id.angles:
                Locale localizacion3 = new Locale("en", "GB");

                Locale.setDefault(localizacion3);
                Configuration config3 = new Configuration();
                config3.locale = localizacion3;
                getBaseContext().getResources().updateConfiguration(config3, getBaseContext().getResources().getDisplayMetrics());
                intent = getIntent();
                finish();
                startActivity(intent);
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

}