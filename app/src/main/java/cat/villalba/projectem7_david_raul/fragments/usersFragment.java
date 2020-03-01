package cat.villalba.projectem7_david_raul.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import cat.villalba.projectem7_david_raul.adapters.ContactesAdapter;

public class usersFragment extends Fragment {

private RecyclerView recyclerView;
private ContactesAdapter contactesAdapter;
private List<Contacte> contactes;
private Map<String,String> amics;
private Contacte contacte;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewContactes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        contactes = new ArrayList<>();
        llegirContactes();
        return view;
    }

    private void llegirContactes() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contacte = dataSnapshot.getValue(Contacte.class);
                amics = contacte.getAmics();

                System.out.println(contacte.getId());
                for (String url : amics.keySet())
                    System.out.println("value: " + url);

                DatabaseReference referenceAmics = FirebaseDatabase.getInstance().getReference("Users");
                referenceAmics.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        contactes.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Contacte contacte = snapshot.getValue(Contacte.class);
                            assert contacte != null;
                            assert firebaseUser != null;

                            if (amics.containsKey(contacte.getId())) {
                                contactes.add(contacte);
                            }
                        }

                        contactesAdapter = new ContactesAdapter(getContext(), contactes);
                        recyclerView.setAdapter(contactesAdapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }


}
