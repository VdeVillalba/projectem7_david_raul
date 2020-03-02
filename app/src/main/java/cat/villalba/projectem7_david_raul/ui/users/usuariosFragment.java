package cat.villalba.projectem7_david_raul.ui.users;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.adapters.Contacte;
import cat.villalba.projectem7_david_raul.adapters.Resenya;
import cat.villalba.projectem7_david_raul.adapters.ResenyasAdapter;


public class usuariosFragment extends Fragment {

    TextView tvCorreo;
    RecyclerView rvResenyas;
    ResenyasAdapter resenyasAdapter;
    private List<Resenya> mTusResenyas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);
        tvCorreo = view.findViewById(R.id.tvCorreo);
        rvResenyas = view.findViewById(R.id.recyclerView);
        rvResenyas.setHasFixedSize(true);
        rvResenyas.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        tvCorreo.setText(user.getEmail());
        mTusResenyas = new ArrayList<>();
        recuperarTusResenyes();

        return view;
    }

    public void recuperarTusResenyes(){
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference referenceUser = FirebaseDatabase.getInstance().getReference("Resenyes");
        referenceUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTusResenyas.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Resenya resenya = snapshot.getValue(Resenya.class);
                    assert resenya != null;
                    assert firebaseUser != null;

                    if (resenya.equals(firebaseUser.getEmail())) {
                        mTusResenyas.add(resenya);
                    }

                }

                resenyasAdapter = new ResenyasAdapter(getContext(), mTusResenyas);
                rvResenyas.setAdapter(resenyasAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
