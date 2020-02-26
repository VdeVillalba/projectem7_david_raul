package cat.villalba.projectem7_david_raul.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import cat.villalba.projectem7_david_raul.R;

public class MissatgesAdapters extends RecyclerView.Adapter<MissatgesAdapters.ViewHolder> {

    public static int MSG_TYPE_RECEPTOR = 0;
    public static int MSG_TYPE_REMITENT = 1;

    private Context mContext;
    private List<Missatge> mMissatge;

    FirebaseUser firebaseUser;

    public MissatgesAdapters(Context mContext, List<Missatge> mMissatge) {
        this.mMissatge = mMissatge;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MissatgesAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == MSG_TYPE_REMITENT) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_remitente, parent, false);
            return new MissatgesAdapters.ViewHolder(view);

        } else {

            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_receptor, parent, false);
            return new MissatgesAdapters.ViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull MissatgesAdapters.ViewHolder holder, int position) {

        Missatge missatge = mMissatge.get(position);

        holder.mostra_missatge.setText(missatge.getMissatge());

    }

    @Override
    public int getItemCount() {
        return mMissatge.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mostra_missatge;

        public ViewHolder(View view) {
            super(view);

            mostra_missatge = view.findViewById(R.id.mostra_missatge);

        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mMissatge.get(position).getRemitent().equals(firebaseUser.getUid())) {
            return MSG_TYPE_REMITENT;
        } else {
            return MSG_TYPE_RECEPTOR;
        }
    }
}

