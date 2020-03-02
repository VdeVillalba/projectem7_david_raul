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

import java.util.List;

import cat.villalba.projectem7_david_raul.R;
import cat.villalba.projectem7_david_raul.activities.ResenyaActivity;


public class ResenyasAdapter extends RecyclerView.Adapter<ResenyasAdapter.ViewHolder> {

    private Context mContext;
    private List<Resenya> mResenyes;

    public ResenyasAdapter(Context mContext, List<Resenya> mResenyes) {
        this.mResenyes = mResenyes;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ResenyasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.resenya_item, parent, false);
        return new ResenyasAdapter.ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ResenyasAdapter.ViewHolder holder, int position) {
        final Resenya resenya = mResenyes.get(position);
        final String notificacio = mContext.getString(R.string.primera_part_missatge) + resenya.getUsuariId() + mContext.getString(R.string.segona_part_missatge) +
                resenya.getPeliculaId();

        holder.resenya.setText(notificacio);
        holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ResenyaActivity.class);
                intent.putExtra("Resenya", resenya.getResenyaId());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mResenyes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView resenya;
        public ImageView profile_image;

        public ViewHolder(View view) {
            super(view);

            resenya = view.findViewById(R.id.resenya);
            profile_image = view.findViewById(R.id.profile_image);
        }
    }
}
