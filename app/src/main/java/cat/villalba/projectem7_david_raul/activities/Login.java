package cat.villalba.projectem7_david_raul.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cat.villalba.projectem7_david_raul.R;

public class Login extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    Button btn_acceso;
    TextView txt_registro;
    EditText edUser, ed_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btn_acceso = findViewById(R.id.btn_acceso);
        txt_registro = findViewById(R.id.txt_registro);

        edUser = findViewById(R.id.ed_user);
        ed_pass = findViewById(R.id.ed_resenya);

        btn_acceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter(edUser.getText().toString(), ed_pass.getText().toString());
                btn_acceso.setEnabled(false);
            }
        });

        if (mUser != null) {
            Intent d = new Intent(getApplicationContext(), pantalla_principal.class);
            startActivity(d);
            finish();
        }

    }

    public void registro(View view){
        btn_acceso = findViewById(R.id.btn_acceso);
        Intent t = new Intent(this,Registro.class);
        txt_registro = findViewById(R.id.txt_registro);
        startActivity(t);
    }


    private void enter(String email, String password){
        btn_acceso.setEnabled(false);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent d = new Intent(getApplicationContext(), pantalla_principal.class);
                            startActivity(d);
                            finish();

                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Usuari o contrasenya incorrecta", Toast.LENGTH_SHORT);
                            toast.show();
                            btn_acceso.setEnabled(true);

                        }

                        // ...
                    }
                });
    }

}
