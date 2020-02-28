package cat.villalba.projectem7_david_raul.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.adapters.Peli;
import cat.villalba.projectem7_david_raul.adapters.Resenya;

public class ResenyaActivity extends AppCompatActivity {

    private Intent intent;
    private RatingBar diversitat;
    private RatingBar cultural;
    private RatingBar genere;
    private RatingBar lgtbi;
    private TextView veure_resenya;
    private ImageView peliImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenya);
        intent = getIntent();

        final String idResenya = intent.getStringExtra("Resenya");
        diversitat = findViewById(R.id.estrelles_funcional);
        cultural = findViewById(R.id.estrelles_cultural);
        genere = findViewById(R.id.estrelles_genere);
        lgtbi = findViewById(R.id.estrelles_lgtbi);
        veure_resenya = findViewById(R.id.resenya);
        peliImagen = findViewById(R.id.imatge_pelicula);
        iniciaResenya(idResenya);


    }


    private void iniciaResenya(final String idResenya) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Resenyes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final Resenya resenya = snapshot.getValue(Resenya.class);
                    assert resenya != null;
                    if (resenya.getResenyaId().equals(idResenya)) {

                        diversitat.setRating((float)resenya.getNotaDiversitat());
                        cultural.setRating((float)resenya.getNotaCultural());
                        genere.setRating((float)resenya.getNotaConsciencia());
                        lgtbi.setRating((float)resenya.getNotaLgti());
                        veure_resenya.setText(resenya.getTextResenya());

                        DatabaseReference reference_peli = FirebaseDatabase.getInstance().getReference("Pelis");
                        reference_peli.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Peli peli = snapshot.getValue(Peli.class);
                                    assert peli != null;

                                    if (peli.getTitulo().equals(resenya.getPeliculaId())) {
                                        Glide.with(getApplicationContext()).load(peli.getImageResource()).into(peliImagen);
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
