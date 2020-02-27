package cat.villalba.projectem7_david_raul.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import cat.villalba.projectem7_david_raul.adapters.Contacte;
import cat.villalba.projectem7_david_raul.adapters.ContactesAdapter;
import cat.villalba.projectem7_david_raul.adapters.Missatge;


public class chatsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContactesAdapter contactesAdapter;
    private List<Contacte> mContactes;

    FirebaseUser firebaseUser;
    DatabaseReference reference;

    private List<String> contactesList;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        contactesList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Xats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contactesList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Missatge missatge = snapshot.getValue(Missatge.class);

                    if (missatge.getRemitent().equals(firebaseUser.getUid())) {
                        contactesList.add(missatge.getDestinatari());
                    }

                    if (missatge.getDestinatari().equals(firebaseUser.getUid())) {
                        contactesList.add(missatge.getRemitent());
                    }
                }

                llegirXats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void llegirXats() {
        mContactes = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mContactes.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Contacte contacte = snapshot.getValue(Contacte.class);

                    for (String id : contactesList) {
                        if(contacte.getId().equals(id)) {
                            if(mContactes.size() != 0) {
                                for (Contacte contacte1 : mContactes) {
                                    if (!contacte.getId().equals(contacte1.getId())) {
                                        mContactes.add(contacte);
                                    }
                                }
                            } else {
                                mContactes.add(contacte);
                            }
                        }
                    }

                }

                contactesAdapter = new ContactesAdapter(getContext(), mContactes);
                recyclerView.setAdapter(contactesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
