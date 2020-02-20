package cat.villalba.projectem7_david_raul;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VotarActivity extends AppCompatActivity {

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
