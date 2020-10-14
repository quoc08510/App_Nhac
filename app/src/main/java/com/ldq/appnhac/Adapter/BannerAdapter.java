package com.ldq.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ldq.appnhac.Activity.DanhSachBaiHatActivity;
import com.ldq.appnhac.Model.Quangcao;
import com.ldq.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Quangcao> arrayListbanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        // định hình và gán dữ liệu cho mõi object
        LayoutInflater inflater= LayoutInflater.from(context);

        View view= inflater.inflate(R.layout.dong_banner,null);

        ImageView imgbackgroundbanner= view.findViewById(R.id.imageViewbackgroundbannner);
        ImageView imgsongbanner = view.findViewById(R.id.imageViewbanner);
        TextView txttitlesongbanner= view.findViewById(R.id.textViewTitleBannerBaihat);
        TextView txtnoidung= view.findViewById(R.id.textviewNoidung);

        Picasso.with(context).load(arrayListbanner.get(position).getHinhAnh()).into(imgbackgroundbanner);
        Picasso.with(context).load(arrayListbanner.get(position).getHinhBaiHat()).into(imgsongbanner);
        txttitlesongbanner.setText(arrayListbanner.get(position).getTenBaiHat());
        txtnoidung.setText(arrayListbanner.get(position).getNoiDung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("banner",arrayListbanner.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
