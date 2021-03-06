package cat.villalba.projectem7_david_raul.ui.social;

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
import java.util.Map;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.adapters.Contacte;
import cat.villalba.projectem7_david_raul.adapters.Resenya;
import cat.villalba.projectem7_david_raul.adapters.ResenyasAdapter;

public class socialFragment extends Fragment {

    private RecyclerView recyclerView;
    private ResenyasAdapter resenyasAdapter;
    private List<Resenya> mResenyas;
    private Map<String,String> amics;
    private Contacte contacte;



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
        DatabaseReference referenceUser = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        referenceUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contacte = dataSnapshot.getValue(Contacte.class);
                amics = contacte.getAmics();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Resenyes");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mResenyas.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Resenya resenya = snapshot.getValue(Resenya.class);
                            assert resenya != null;
                            assert firebaseUser != null;

                            if (amics.containsValue(resenya.getUsuariId())) {
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

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}