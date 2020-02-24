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
import cat.villalba.projectem7_david_raul.activities.Contacte;
import cat.villalba.projectem7_david_raul.activities.Mensajeria;

public class ContactesAdapter extends RecyclerView.Adapter<ContactesAdapter.ViewHolder> {

    private Context mContext;
    private List<Contacte> mUsers;

    public ContactesAdapter(Context mContext, List<Contacte> mUsers) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.contacte_item, parent, false);
        return new ContactesAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Contacte contacte = mUsers.get(position);
        holder.nomContacte.setText(contacte.getId());
        //Aqui iria si quiero crear una imagen de perfil
        holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Mensajeria.class);
                intent.putExtra("Usuari", contacte.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nomContacte;
        public ImageView profile_image;

        public ViewHolder(View view) {
            super(view);

            nomContacte = view.findViewById(R.id.contacte);
            profile_image = view.findViewById(R.id.profile_image);
        }
    }
}
