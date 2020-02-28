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

import java.util.ArrayList;
import java.util.List;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.activities.Mensajeria;
import cat.villalba.projectem7_david_raul.activities.VotarActivity;


public class adaptadorPelis extends RecyclerView.Adapter<adaptadorPelis.ViewHolder> {

    private Context mContext;
    private List<Peli> mPelis;

    public adaptadorPelis(Context mContext, List<Peli> mPelis) {
        this.mPelis = mPelis;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public adaptadorPelis.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pelicula_item, parent, false);
        return new adaptadorPelis.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull adaptadorPelis.ViewHolder holder, int position) {
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
                Intent intent = new Intent(mContext, VotarActivity.class);
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
