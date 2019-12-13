package cat.villalba.projectem7_david_raul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    private EditText usuario;
    private EditText pass1;
    private EditText pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_registro);
        usuario = findViewById(R.id.etApodo);
        pass1 = findViewById(R.id.etContraseña1);
        pass2 = findViewById(R.id.etContraseña2);

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
        String apodo = usuario.getText().toString();
        String contra = pass1.getText().toString();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Usuario y contraseña", 0);
        SharedPreferences.Editor editor = pref.edit();

        Intent intent = new Intent(this, MainActivity.class);

        if((comprobarContrasena(pass1, pass2) == true)){
            editor.putString("usuario", apodo);
            editor.putString("contraseña", contra);
            editor.commit();
            Toast toast = Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(this, "Error; Contraseña incorrecta", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void volver(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
