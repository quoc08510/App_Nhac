package com.ldq.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldq.appnhac.Activity.DanhSachBaiHatActivity;
import com.ldq.appnhac.Model.Playlist;
import com.ldq.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterDanhSachCacPlayList extends RecyclerView.Adapter<AdapterDanhSachCacPlayList.ViewHolder>{
    Context context;
    ArrayList<Playlist> arrayListPlaylist;
    public AdapterDanhSachCacPlayList(Context context, ArrayList<Playlist> arrayListPlaylist) {
        this.context = context;
        this.arrayListPlaylist = arrayListPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_danh_sach_cac_playlist,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist =arrayListPlaylist.get(position);
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.imgHinhnen);
        holder.txttenplaylist.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return arrayListPlaylist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhnen;
        TextView txttenplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhnen = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            txttenplaylist=itemView.findViewById(R.id.textviewtendanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist",arrayListPlaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
