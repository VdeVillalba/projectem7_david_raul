package cat.villalba.projectem7_david_raul.ui.amics;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
import cat.villalba.projectem7_david_raul.adapters.Solicitud;
import cat.villalba.projectem7_david_raul.adapters.solicitudsAdapter;

public class amicsFragment extends Fragment {

    private RecyclerView recyclerView;
    private solicitudsAdapter solicitudsAdapter;
    private List<Contacte> contactes;
    private List<Contacte> contactesTemporal;
    private Solicitud solicitud;
    private Contacte contacte;
    private EditText contacteInvitar;
    private Button btn_envia;
    private AlertDialog alertDialog;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private List<Solicitud> solicituds;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_amics, container, false);
        solicituds = new ArrayList<>();
        contactesTemporal = new ArrayList<>();
        btn_envia = view.findViewById(R.id.btn_afegir);
        btn_envia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaSolicitud(v);
            }
        });
        alertDialog = new AlertDialog.Builder(getContext()).create();
        contacteInvitar = view.findViewById(R.id.text_adreca);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contactes = new ArrayList<>();
        llegirSolicituds();
        return view;
    }

    public void enviaSolicitud(View view) {
        DatabaseReference referenceEmail = FirebaseDatabase.getInstance().getReference("Users");
        referenceEmail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Contacte contacte = snapshot.getValue(Contacte.class);
                    assert contacte != null;
                    assert firebaseUser != null;

                    if (contacte.getNomContacte().equals(contacteInvitar.getText().toString())) {
                        String codiPeticio = firebaseUser.getUid() + contacte.getId();
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Peticions").child(codiPeticio);
                        Solicitud solicitud = new Solicitud(codiPeticio, firebaseUser.getUid(), contacte.getId());
                        reference.setValue(solicitud);
                        contacteInvitar.setText("");

                        Toast.makeText(getContext(), (getContext().getString(R.string.enviada_correctament)),
                                Toast.LENGTH_SHORT).show();

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void llegirSolicituds() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Peticions");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    solicitud = snapshot.getValue(Solicitud.class);

                    assert solicitud != null;
                    assert firebaseUser != null;

                    solicituds.add(solicitud);

                }

                DatabaseReference referenceUsuaris = FirebaseDatabase.getInstance().getReference("Users");
                referenceUsuaris.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            contactes.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                contacte = snapshot.getValue(Contacte.class);
                                contactesTemporal.add(contacte);
                                assert contacte != null;
                            }

                            for (Solicitud solicitud : solicituds) {

                                for (Contacte contacte : contactesTemporal) {

                                    if (solicitud.getEmisor().equals(contacte.getId()) && solicitud.getReceptor().equals(firebaseUser.getUid())) {
                                        contactes.add(contacte);

                                    }

                                }


                            }

                            solicitudsAdapter = new solicitudsAdapter(getContext(), contactes);
                            recyclerView.setAdapter(solicitudsAdapter);
                            solicitudsAdapter.notifyDataSetChanged();

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
