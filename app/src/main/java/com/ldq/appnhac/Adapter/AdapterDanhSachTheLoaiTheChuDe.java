package com.ldq.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldq.appnhac.Activity.DanhSachBaiHatActivity;
import com.ldq.appnhac.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.ldq.appnhac.Model.TheLoai;
import com.ldq.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterDanhSachTheLoaiTheChuDe  extends RecyclerView.Adapter<AdapterDanhSachTheLoaiTheChuDe.ViewHolder>{
   Context context;
    ArrayList<TheLoai> arrayTheloai;

    public AdapterDanhSachTheLoaiTheChuDe(Context context, ArrayList<TheLoai> arrayTheloai) {
        this.context = context;
        this.arrayTheloai = arrayTheloai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_the_loai_theo_chu_de,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        TheLoai theLoai = arrayTheloai.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imgHinhtheloai);
        holder.txtTentheloai.setText(theLoai.getTenTheLoai());

        holder.imgHinhtheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("idtheloai",arrayTheloai.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayTheloai.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhtheloai;
        TextView txtTentheloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhtheloai = itemView.findViewById(R.id.imageviewtheloaitheochude);
            txtTentheloai = itemView.findViewById(R.id.textviewtentheoloaitheochude);
        }
    }
}
