package cat.villalba.projectem7_david_raul.ui.users;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cat.villalba.projectem7_david_raul.R;


public class usuariosFragment extends Fragment {

    TextView tvCorreo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);
        tvCorreo = view.findViewById(R.id.tvCorreo);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        tvCorreo.setText(user.getEmail());

        return view;
    }

}
