package cat.villalba.projectem7_david_raul.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.adapters.Peli;
import cat.villalba.projectem7_david_raul.adapters.Resenya;


public class VotarActivity extends AppCompatActivity {

    private RatingBar diversitat;
    private RatingBar cultural;
    private RatingBar genere;
    private RatingBar lgtbi;
    private EditText resenya_edit;
    private ImageView peliImagen;
    private TextView peli_titol;
    private TextView sinopsi;
    private Peli peli_actual;
    private Intent intent;
    private DatabaseReference reference;
    private AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar);
        intent = getIntent();
        String idPeli = intent.getStringExtra("Titol");
        iniciaPeli(idPeli);

        peliImagen = findViewById(R.id.peliResenya);
        peli_titol = findViewById(R.id.titol);
        sinopsi = findViewById(R.id.sinopsi);
        diversitat = findViewById(R.id.estrelles_funcional);
        cultural = findViewById(R.id.estrelles_cultural);
        genere = findViewById(R.id.estrelles_genere);
        lgtbi = findViewById(R.id.estrelles_lgtbi);
        resenya_edit = findViewById(R.id.ed_resenya);
        builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.missatge_resenya);



    }

    public void guardaResenya(View view) {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String idResenya = peli_actual.getTitulo() + firebaseUser.getUid();

        Resenya resenya = new Resenya(idResenya, firebaseUser.getEmail(), peli_actual.getTitulo(), resenya_edit.getText().toString(),
                (long) diversitat.getRating(), (long) lgtbi.getRating(), (long) genere.getRating(), (long) cultural.getRating());

        reference = FirebaseDatabase.getInstance().getReference("Resenyes").child(idResenya);
        reference.setValue(resenya).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(VotarActivity.this, pantalla_principal.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                    builder.create();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(VotarActivity.this, "Error en la creació de la resenya.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void iniciaPeli(final String idPeli) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Pelis");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Peli peli = snapshot.getValue(Peli.class);
                    assert peli != null;
                    if (peli.getTitulo().equals(idPeli)) {
                        peli_actual = peli;
                        Glide.with(getApplicationContext()).load(peli_actual.getImageResource()).into(peliImagen);
                        peli_titol.setText(peli_actual.getTitulo());
                        sinopsi.setText(peli_actual.getSinopsis());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
