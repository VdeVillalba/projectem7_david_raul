package cat.villalba.projectem7_david_raul.ui.totesPelicules;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.adapters.Peli;
import cat.villalba.projectem7_david_raul.adapters.adaptadorPelis;

public class TotesPelis extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<Peli> mPeliculas;
    private adaptadorPelis mAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mPeliculas = new ArrayList<>();
        mAdapter = new adaptadorPelis(getContext(), mPeliculas);

        mRecyclerView.setAdapter(mAdapter);

        initializeData();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mPeliculas, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mPeliculas.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        });

        helper.attachToRecyclerView(mRecyclerView);

        return root;

    }

    private void initializeData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Pelis");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPeliculas.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Peli peli = snapshot.getValue(Peli.class);

                    assert peli != null;
                    mPeliculas.add(peli);

                }

                mAdapter = new adaptadorPelis(getContext(), mPeliculas);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}


