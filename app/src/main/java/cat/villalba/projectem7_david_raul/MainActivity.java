package cat.villalba.projectem7_david_raul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegistro = findViewById(R.id.txt_registro);


    }

    public void registro(View view){
        Intent t = new Intent(this,Registro.class);
        startActivity(t);
    }
}
