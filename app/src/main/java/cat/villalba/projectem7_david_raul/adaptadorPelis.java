package cat.villalba.projectem7_david_raul;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class adaptadorPelis extends RecyclerView.Adapter<adaptadorPelis.ViewHolder> {

    private ArrayList<Peli> mPeliculas;
    private Context mContext;

    adaptadorPelis(Context context, ArrayList<Peli> peliculas) {
        this.mPeliculas = peliculas;
        this.mContext = context;
    }

    @Override
    public adaptadorPelis.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.pelicula_lista,parent, false));
    }

    @Override
    public void onBindViewHolder(adaptadorPelis.ViewHolder holder, int position) {

        Peli peliActual = mPeliculas.get(position);
        holder.bindTo(peliActual);
    }

    @Override
    public int getItemCount() {return mPeliculas.size();}

    class ViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        private TextView mTitulo;
        private TextView mInfo;
        private TextView mResumen;
        private TextView mDirector;
        private ImageView mPeliImagen;


        ViewHolder(View itemView) {
            super(itemView);


            mTitulo = (TextView)itemView.findViewById(R.id.title);
            mInfo = (TextView) itemView.findViewById(R.id.argumento);
            mDirector = (TextView) itemView.findViewById(R.id.Director);
            mPeliImagen = itemView.findViewById(R.id.peliImagen);
            itemView.setOnClickListener(this);
        }

        void bindTo(Peli peliActual) {
            mTitulo.setText(peliActual.getTitulo());
            mInfo.setText(peliActual.getInfo());
            mDirector.setText(peliActual.getDirector());
            Glide.with(mContext).load(peliActual.getImageResource()).into(mPeliImagen);
        }

        @Override
        public void onClick(View view) {
            Peli peliActual = mPeliculas.get(getAdapterPosition());
            Intent pantallaVotar = new Intent(mContext, VotarActivity.class);
            pantallaVotar.putExtra("title", peliActual.getTitulo());
            /*detailIntent.putExtra("image_resource",
                    peliActual.getImageResource());
            detailIntent.putExtra("argumento", peliActual.getResumen());
            detailIntent.putExtra("Director",peliActual.getDirector());*/
            mContext.startActivity(pantallaVotar);

        }
    }
}