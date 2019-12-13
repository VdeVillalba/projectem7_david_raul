package cat.villalba.projectem7_david_raul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mRegistro;
    private EditText mUser, mPass;
    Boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegistro = findViewById(R.id.txt_registro);
        mUser = findViewById(R.id.ed_user);
        mPass = findViewById(R.id.ed_pass);

    }

    public void registro(View view){
        Intent t = new Intent(this,Registro.class);
        startActivity(t);
    }

    public void enter(View view) {
       compruebaCredenciales();
        if(correcto == true) {
            Intent d = new Intent(this, AppEntrada.class);
            startActivity(d);
        } else {
            Toast toast = Toast.makeText(this, "Usuario y/o contraseña incorrecta", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void compruebaCredenciales() {

        SharedPreferences pref = getSharedPreferences("Usuario y contraseña", MODE_PRIVATE);
        String usuario = pref.getString("usuario","Error, no existe.");
        String pass = pref.getString("contraseña","Error, no existe.");
        if(mUser.getText().toString().equals(usuario) && mPass.getText().toString().equals(pass)) {
            correcto = true;
        } else {
            correcto = false;
        }

    }
}
