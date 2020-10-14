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

public class AdapterAllAlbum  extends RecyclerView.Adapter<AdapterAllAlbum.ViewHolder>{
    Context context;
    ArrayList<Album> arrayAlbum;

    public AdapterAllAlbum(Context context, ArrayList<Album> arrayAlbum) {
        this.context = context;
        this.arrayAlbum = arrayAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_all_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = arrayAlbum.get(position);
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imghinhallalbum);
        holder.txtTenallalbum.setText(album.getTenAlbum());


    }

    @Override
    public int getItemCount() {
        return arrayAlbum.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhallalbum;
        TextView txtTenallalbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imghinhallalbum= itemView.findViewById(R.id.imageviewallalbum);
            txtTenallalbum=itemView.findViewById(R.id.textviewtenallalbum);

            imghinhallalbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idalbum",arrayAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
