package cat.villalba.projectem7_david_raul.ui.social;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.adapters.Resenya;
import cat.villalba.projectem7_david_raul.adapters.ResenyasAdapter;

public class socialFragment extends Fragment {

    private RecyclerView recyclerView;
    private ResenyasAdapter resenyasAdapter;
    private List<Resenya> mResenyas;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_social, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mResenyas = new ArrayList<>();
        recuperarResenyes();
        return root;
    }

    private void recuperarResenyes() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Resenyes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mResenyas.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Resenya resenya = snapshot.getValue(Resenya.class);
                    System.out.println(resenya.getTextResenya());
                    assert resenya != null;
                    assert firebaseUser != null;

                    if (!resenya.getUsuariId().equals(firebaseUser.getUid())) {
                        //PLANTEAR AQUI PARA CONDICIONAR EL METER SOLO LA DE AMIGOS
                        mResenyas.add(resenya);
                    }
                }

                resenyasAdapter = new ResenyasAdapter(getContext(), mResenyas);
                recyclerView.setAdapter(resenyasAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}