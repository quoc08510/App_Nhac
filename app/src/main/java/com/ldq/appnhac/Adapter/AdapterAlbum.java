package com.ldq.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldq.appnhac.Activity.DanhSachBaiHatActivity;
import com.ldq.appnhac.Model.Album;
import com.ldq.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterAlbum extends RecyclerView.Adapter<AdapterAlbum.ViewHolder>{
    Context context;
    ArrayList<Album> mangalabum;

    public AdapterAlbum(Context context, ArrayList<Album> mangalabum) {
        this.context = context;
        this.mangalabum = mangalabum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = mangalabum.get(position);
        holder.txtCasiAlbum.setText(album.getTenAlbum());
        holder.txtTenAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinhAlbum);
    }

    @Override
    public int getItemCount() {
        return mangalabum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhAlbum;
        TextView txtTenAlbum,txtCasiAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAlbum= itemView.findViewById(R.id.imageviewAlbum);
            txtTenAlbum = itemView.findViewById(R.id.textviewTenAlbum);
            txtCasiAlbum= itemView.findViewById(R.id.textviewtencasialbum);
            imgHinhAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idalbum",mangalabum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
