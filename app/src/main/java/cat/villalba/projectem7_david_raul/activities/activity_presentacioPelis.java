package cat.villalba.projectem7_david_raul.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.List;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.adapters.Contacte;
import cat.villalba.projectem7_david_raul.adapters.Peli;
import cat.villalba.projectem7_david_raul.adapters.Resenya;
import cat.villalba.projectem7_david_raul.adapters.Solicitud;

public class activity_presentacioPelis extends AppCompatActivity {


    private RatingBar diversitat;
    private RatingBar cultural;
    private RatingBar genere;
    private RatingBar lgtbi;
    private ImageView peliImagen;
    private TextView peli_titol;
    private TextView sinopsi;
    private Peli peli_actual;
    private Intent intent;
    private AlertDialog alertDialog;
    private Context mContext = this;
    private List<String> interessos;
    private FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
    private Contacte contacte;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_presentacio);
        String idPeli = intent.getStringExtra("Titol");
        iniciaPeli(idPeli);
        calculaMedias(idPeli);
        peliImagen = findViewById(R.id.peliResenya);
        peli_titol = findViewById(R.id.titol);
        sinopsi = findViewById(R.id.sinopsi);
        diversitat = findViewById(R.id.estrelles_funcional);
        cultural = findViewById(R.id.estrelles_cultural);
        genere = findViewById(R.id.estrelles_genere);
        lgtbi = findViewById(R.id.estrelles_lgtbi);
        alertDialog = new AlertDialog.Builder(mContext).create();


    }

    public void afegirInteressos(View view) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Users").child(firebaseUser.getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contacte = dataSnapshot.getValue(Contacte.class);
                interessos = contacte.getInteressos();
                interessos.add(peli_titol.getText().toString());
                DatabaseReference referenceInteressos = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("interessos");
                referenceInteressos.setValue(interessos).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(activity_presentacioPelis.this, (mContext.getString(R.string.interessos_afegits)),
                                    Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(activity_presentacioPelis.this, "Error",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private void calculaMedias(final String idPeli) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Resenyes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                float mitjanaDiversitat = 0;
                float mitjanaGenere = 0;
                float mitjanaCultural = 0;
                float mitjanaLGTBI = 0;
                float comptador = 0;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Resenya resenya = snapshot.getValue(Resenya.class);
                    assert resenya != null;
                    if (resenya.getPeliculaId().equals(idPeli)) {
                        comptador++;
                        mitjanaDiversitat += (float) resenya.getNotaDiversitat();
                        mitjanaGenere += (float) resenya.getNotaConsciencia();
                        mitjanaCultural += (float) resenya.getNotaCultural();
                        mitjanaLGTBI += (float) resenya.getNotaLgti();

                    }

                }

                mitjanaDiversitat /= comptador;
                mitjanaGenere /= comptador;
                mitjanaCultural /= comptador;
                mitjanaLGTBI /= comptador;

                diversitat.setRating(mitjanaDiversitat);
                cultural.setRating(mitjanaCultural);
                genere.setRating(mitjanaGenere);
                lgtbi.setRating(mitjanaLGTBI);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
