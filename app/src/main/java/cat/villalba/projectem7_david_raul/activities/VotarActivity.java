package cat.villalba.projectem7_david_raul.activities;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import cat.villalba.projectem7_david_raul.adapters.BaseActivity;
import cat.villalba.projectem7_david_raul.R;

public class VotarActivity extends BaseActivity {

    private RatingBar estrelles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar);



        /*TextView peliTitulo = findViewById(R.id.titleDetail);
        TextView peliResumen = findViewById(R.id.argumentoDetail);
        TextView peliDirector = findViewById(R.id.directorDetail);
        ImageView peliImagen = findViewById(R.id.peliImagenDetail);

        peliTitulo.setText(getIntent().getStringExtra("title"));
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(peliImagen);

        peliResumen.setText(getIntent().getStringExtra("argumento"));
        peliDirector.setText(getIntent().getStringExtra("Director"));*/


    }

    public void mostrarEleccion(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }


}
