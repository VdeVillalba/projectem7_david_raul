package cat.villalba.projectem7_david_raul;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class VotarActivity extends AppCompatActivity {

SeekBar barraNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar);
        barraNota =(SeekBar)findViewById(R.id.sb_notas);
        barraNota.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progreso = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progreso = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(VotarActivity.this, "Nota:" + progreso,
                        Toast.LENGTH_SHORT).show();
            }
        });
        /*TextView peliTitulo = findViewById(R.id.titleDetail);
        TextView peliResumen = findViewById(R.id.argumentoDetail);
        TextView peliDirector = findViewById(R.id.directorDetail);
        ImageView peliImagen = findViewById(R.id.peliImagenDetail);

        peliTitulo.setText(getIntent().getStringExtra("title"));
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(peliImagen);

        peliResumen.setText(getIntent().getStringExtra("argumento"));
        peliDirector.setText(getIntent().getStringExtra("Director"));*/


    }

    public void mostrarEleccion(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.rb_nada:
                if (checked)
                    // Same day service
                    mostrarEleccion(getString(R.string.rb1));
                break;
            case R.id.rb_poco:
                if (checked)
                    // Next day delivery
                    mostrarEleccion(getString(R.string.rb2));
                break;
            case R.id.rb_bien:
                if (checked)
                    // Pick up
                    mostrarEleccion(getString(R.string.rb3));
                break;
            case R.id.rb_mucho:
                if (checked)
                    // Pick up
                    mostrarEleccion(getString(R.string.rb4));
                break;
            default:
                // Do nothing.
                break;
        }
    }
}
