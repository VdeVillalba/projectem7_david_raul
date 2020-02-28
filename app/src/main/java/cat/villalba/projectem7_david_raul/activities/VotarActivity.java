package cat.villalba.projectem7_david_raul.activities;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import cat.villalba.projectem7_david_raul.adapters.BaseActivity;
import cat.villalba.projectem7_david_raul.R;

public class VotarActivity extends BaseActivity {

    private RatingBar estrelles;
    private ImageView peli_image;
    private TextView peli_titol;

    DatabaseReference reference;
    FirebaseUser firebaseUser;

    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    private Uri image_Uri;
    private StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar);

        storageReference = FirebaseStorage.getInstance().getReference();

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
