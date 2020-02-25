package cat.villalba.projectem7_david_raul.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import cat.villalba.projectem7_david_raul.R;

public class Mensajeria extends AppCompatActivity {

    ImageView profile_image;
    TextView nomUsuari;

    FirebaseUser firebaseUseruser;
    DatabaseReference reference;

    ImageButton btn_envia;
    EditText text_mensaje;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajeria);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_envia = findViewById(R.id.btn_enviar);
        text_mensaje = findViewById(R.id.ed_mensaje);
        profile_image = findViewById(R.id.profile_image);
        nomUsuari = findViewById(R.id.contacte);

        intent = getIntent();
        final String idUsuari = intent.getStringExtra("Usuari");


        btn_envia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = text_mensaje.getText().toString();
                if (!msg.equals("")) {
                    enviaMensaje(firebaseUseruser.getUid(), idUsuari, msg);
                    text_mensaje.setText("");
                } else {
                    Toast.makeText(Mensajeria.this,"No pots enviar missatges buits", Toast.LENGTH_SHORT);
                }
            }
        });

        firebaseUseruser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(idUsuari);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Contacte contacte = dataSnapshot.getValue(Contacte.class);
                nomUsuari.setText(contacte.getNomContacte());
                if (contacte.getImageURL().equals("default")) {
                    profile_image.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(Mensajeria.this).load(contacte.getImageURL()).into(profile_image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void enviaMensaje(String remitent, String destinatari, String missatge) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("remitent", remitent);
        hashMap.put("destinatari", destinatari);
        hashMap.put("missatge", missatge);

        reference.child("Xats").push().setValue(hashMap);

    }
}
