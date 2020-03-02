package cat.villalba.projectem7_david_raul.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cat.villalba.projectem7_david_raul.R;


public class solicitudsAdapter extends RecyclerView.Adapter<solicitudsAdapter.ViewHolder> {

    private AlertDialog alertDialog;
    private Context mContext;
    private List<Contacte> mUsers;
    private Contacte contacte;

    public solicitudsAdapter(Context mContext, List<Contacte> mUsers) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public solicitudsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.solicitud_item, parent, false);
        return new solicitudsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull solicitudsAdapter.ViewHolder holder, int position) {
        contacte = mUsers.get(position);
        holder.nomContacte.setText(contacte.getNomContacte());
        alertDialog = new AlertDialog.Builder(mContext).create();
        holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                alertDialog.setTitle(mContext.getString(R.string.solicitud));
                alertDialog.setMessage(mContext.getString(R.string.solicitud_text));
                alertDialog.setIcon(R.drawable.ic_addamigowhite);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, (mContext.getString(R.string.cancel)),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, (mContext.getString(R.string.accepta)),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                assert firebaseUser != null;
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users")
                                        .child(firebaseUser.getUid()).child("amics");
                                Map<String,Object> amic = new HashMap<>();
                                amic.put(contacte.getId(), contacte.getNomContacte());
                                reference.updateChildren(amic).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            removeItem(contacte);
                                            String codiPeticio = contacte.getId() + firebaseUser.getUid();
                                            System.out.println(codiPeticio);
                                            DatabaseReference referencePeticio = FirebaseDatabase.getInstance().getReference("Peticions").child(codiPeticio);
                                            referencePeticio.removeValue();


                                        }
                                    }
                                });
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, (mContext.getString(R.string.rebutja)),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removeItem(contacte);
                                String codiPeticio = contacte.getId() + firebaseUser.getUid();
                                DatabaseReference referencePeticio = FirebaseDatabase.getInstance().getReference("Peticions").child(codiPeticio);
                                referencePeticio.removeValue();
                            }
                        });
                alertDialog.show();
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

    public void removeItem(Contacte contacte) {


        int currPosition = mUsers.indexOf(contacte);
        mUsers.remove(currPosition);
        notifyItemRemoved(currPosition);

    }
}
