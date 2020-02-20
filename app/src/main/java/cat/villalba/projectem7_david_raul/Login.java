package cat.villalba.projectem7_david_raul;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;

    private FirebaseAuth mAuth;

    Button btn_acceso;
    TextView txt_registro;
    EditText edUser, ed_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btn_acceso = findViewById(R.id.btn_acceso);
        txt_registro = findViewById(R.id.txt_registro);

        edUser = findViewById(R.id.ed_user);
        ed_pass = findViewById(R.id.ed_pass);

        btn_acceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUsuario(edUser.getText().toString(), ed_pass.getText().toString());
                btn_acceso.setEnabled(false);
            }
        });

    }

    public void registro(View view){
        btn_acceso = findViewById(R.id.btn_acceso);
        Intent t = new Intent(this,Registro.class);
        txt_registro = findViewById(R.id.txt_registro);
        startActivity(t);
    }


    private void loginUsuario(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent d = new Intent(getApplicationContext(), pantalla_principal.class);
                            startActivity(d);

                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrecta", Toast.LENGTH_SHORT);
                            toast.show();

                        }

                        // ...
                    }
                });
    }

}
