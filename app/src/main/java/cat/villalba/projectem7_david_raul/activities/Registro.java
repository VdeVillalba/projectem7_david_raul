package cat.villalba.projectem7_david_raul.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.adapters.Contacte;

public class Registro extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference reference;

    private EditText correo;
    private EditText pass1;
    private EditText pass2;

    Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_registro);
        mAuth = FirebaseAuth.getInstance();
        correo = findViewById(R.id.etApodo);
        pass1 = findViewById(R.id.etContraseña1);
        pass2 = findViewById(R.id.etContraseña2);

        btnRegistro = findViewById(R.id.btnRegisrarse);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

    }

    public int comprobarContrasena(EditText pass1, EditText pass2) {
        String part1 = pass1.getText().toString();
        String part2 = pass2.getText().toString();

        if (part1.length() < 6 || part2.length() < 6) {
            return 1;
        } else if (part1.equals(part2) && (!part1.isEmpty()) && (!part2.isEmpty())) {
            return 2;
        } else if (part1.isEmpty() || part2.isEmpty()) {
            return 3;
        } else {
            return 4;
        }
    }

    public void registrarUsuario() {
        String mail = correo.getText().toString();
        String contra = pass1.getText().toString();
        switch (comprobarContrasena(pass1, pass2)) {
            case 1:
                Toast.makeText(Registro.this, "La contrasenya ha de tenir 6 o més caràcters", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                creaUsuario(mail, contra);
                break;
            case 3:
                Toast.makeText(Registro.this, "Error, algún dels camps és buit", Toast.LENGTH_SHORT).show();
                break;

            case 4:
                Toast.makeText(Registro.this, "Les contrasenyes no coincideixen", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(Registro.this, "Error inesperat", Toast.LENGTH_SHORT).show();
        }

    }


    private void creaUsuario(final String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            String userId = user.getUid();
                            Map<String,String> amics = new HashMap<>();
                            amics.put("Inicialitzador", "null");
                            ArrayList<String> interessos = new ArrayList<>();
                            interessos.add("Inicialitzador");
                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                            Contacte contacte = new Contacte(userId, email, "default", amics, interessos);

                            reference.setValue(contacte).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(Registro.this, Login.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });

                        } else {

                            Toast.makeText(Registro.this, "Registre erroni.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    public void volver(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

}
