package cat.villalba.projectem7_david_raul.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import cat.villalba.projectem7_david_raul.R;

public class ResenyaActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenya);

        intent = getIntent();
        final String idResenya = intent.getStringExtra("Resenya");
    }
}
