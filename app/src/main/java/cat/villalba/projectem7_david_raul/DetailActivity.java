package cat.villalba.projectem7_david_raul;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView peliTitulo = findViewById(R.id.titleDetail);
        TextView peliResumen = findViewById(R.id.argumentoDetail);
        TextView peliDirector = findViewById(R.id.directorDetail);
        ImageView peliImagen = findViewById(R.id.peliImagenDetail);

        peliTitulo.setText(getIntent().getStringExtra("title"));
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(peliImagen);

        peliResumen.setText(getIntent().getStringExtra("argumento"));
        peliDirector.setText(getIntent().getStringExtra("Director"));


    }
}
