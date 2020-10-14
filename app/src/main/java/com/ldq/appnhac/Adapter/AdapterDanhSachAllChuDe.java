package com.ldq.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldq.appnhac.Activity.DanhSachTatCaChuDeActivity;
import com.ldq.appnhac.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.ldq.appnhac.Model.Chude;
import com.ldq.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterDanhSachAllChuDe extends RecyclerView.Adapter<AdapterDanhSachAllChuDe.ViewHolder>{
    Context context;
    ArrayList<Chude> arraychude;

    public AdapterDanhSachAllChuDe(Context context, ArrayList<Chude> arraychude) {
        this.context = context;
        this.arraychude = arraychude;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_all_chu_de,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chude chude= arraychude.get(position);
        Picasso.with(context).load(chude.getHinhChuDe()).into(holder.imgchude);
    }

    @Override
    public int getItemCount() {
        return arraychude.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgchude;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imgchude=itemView.findViewById(R.id.imageviewdongallchude);
            imgchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, DanhSachTheLoaiTheoChuDeActivity.class);
                    intent.putExtra("chude",arraychude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
