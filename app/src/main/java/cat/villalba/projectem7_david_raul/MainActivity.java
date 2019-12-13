package cat.villalba.projectem7_david_raul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegistro = findViewById(R.id.txt_registro);


    }

    public void onClick(View v) {
        Toast.makeText(this,R.string.msg_toast,Toast.LENGTH_LONG).show();


    }
}
