package cat.villalba.projectem7_david_raul.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import cat.villalba.projectem7_david_raul.R;

public class Mensajeria extends AppCompatActivity {

    ImageView profile_image;
    TextView nomUsuari;

    FirebaseUser user;
    DatabaseReference reference;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajeria);
    }
}
