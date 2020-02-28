package cat.villalba.projectem7_david_raul.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.activities.activity_presentacioPelis;

public class adaptadorPelisGeneral extends RecyclerView.Adapter<adaptadorPelisGeneral.ViewHolder> {

    private Context mContext;
    private List<Peli> mPelis;

    public adaptadorPelisGeneral(Context mContext, List<Peli> mPelis) {
        this.mPelis = mPelis;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public adaptadorPelisGeneral.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pelicula_item, parent, false);
        return new adaptadorPelisGeneral.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull adaptadorPelisGeneral.ViewHolder holder, int position) {
        final Peli peli = mPelis.get(position);
        holder.titolPeli.setText(peli.getTitulo());


        if (peli.getImageResource().equals("default")) {
            holder.imatgePeli.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(mContext).load(peli.getImageResource()).into(holder.imatgePeli);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, activity_presentacioPelis.class);
                intent.putExtra("Titol", peli.getTitulo());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPelis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titolPeli;
        public ImageView imatgePeli;

        public ViewHolder(View view) {
            super(view);

            titolPeli = view.findViewById(R.id.title);
            imatgePeli = view.findViewById(R.id.peliImagen);
        }
    }
}