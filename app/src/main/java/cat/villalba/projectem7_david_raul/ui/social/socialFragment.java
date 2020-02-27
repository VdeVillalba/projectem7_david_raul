package cat.villalba.projectem7_david_raul.ui.social;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.adapters.ResenyasAdapter;

public class socialFragment extends Fragment {

    private RecyclerView recyclerView;
    private ResenyasAdapter resenyasAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_social, container, false);

        return root;
    }
}