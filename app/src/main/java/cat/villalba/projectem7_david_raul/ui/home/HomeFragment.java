package cat.villalba.projectem7_david_raul.ui.home;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;


import cat.villalba.projectem7_david_raul.Peli;
import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.adaptadorPelis;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView mRecyclerView;
    private ArrayList<Peli> mPeliculas;
    private adaptadorPelis mAdapter;
    private Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

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

        String[] listaPelis = getResources().getStringArray(R.array.pelis_titulos);
        String[] listaDirectores = getResources().getStringArray(R.array.pelis_director);
        TypedArray pelisRecursosImagenes =
                getResources().obtainTypedArray(R.array.pelis_images);

        mPeliculas.clear();

        for (int i = 0; i < listaPelis.length; i++) {
            mPeliculas.add(new Peli(listaPelis[i], pelisRecursosImagenes.getResourceId(i, 0), listaDirectores[i]));
        }

        pelisRecursosImagenes.recycle();

        mAdapter.notifyDataSetChanged();


    }

    public void resetPelis(View view) {
        initializeData();
    }
}


