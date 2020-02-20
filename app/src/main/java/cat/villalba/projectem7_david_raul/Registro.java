package cat.villalba.projectem7_david_raul;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Registro extends AppCompatActivity {

    private static final String TAG = "Registro" ;
    private FirebaseAuth mAuth;

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
                creaUsuario(correo.getText().toString(), pass1.getText().toString());
            }
        });

    }

    public boolean comprobarContrasena(EditText pass1, EditText pass2){
        String part1 = pass1.getText().toString();
        String part2 = pass2.getText().toString();
        boolean resultado;

        if(part1.equals(part2) && (!part1.isEmpty()) && (!part2.isEmpty())){
            resultado = true;
        }else{
            resultado = false;
        }
        return resultado;
    }

    public void registrarUsuario(View view){
        String mail = correo.getText().toString();
        String contra = pass1.getText().toString();

        if((comprobarContrasena(pass1, pass2) == true)){
            creaUsuario(mail, contra);
        }else{
            Toast toast = Toast.makeText(this, "Error; Contraseña incorrecta", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void creaUsuario(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registro.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    public void volver(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

}
