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
import androidx.recyclerview.widget.RecyclerView;

import com.ldq.appnhac.Activity.PlayNhacActivity;
import com.ldq.appnhac.Model.BaiHat;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterBaiHatHot  extends RecyclerView.Adapter<AdapterBaiHatHot.ViewHolder>{
    Context context;
    ArrayList<BaiHat> arrayListbaihathot;

    public AdapterBaiHatHot(Context context, ArrayList<BaiHat> arrayListbaihathot) {
        this.context = context;
        this.arrayListbaihathot = arrayListbaihathot;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_hot,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHatDuocYeuThich = arrayListbaihathot.get(position);
        holder.txtCasi.setText(baiHatDuocYeuThich.getCasi());
        holder.txtTenBaiHat.setText(baiHatDuocYeuThich.getTenbaihat());
        Picasso.with(context).load(baiHatDuocYeuThich.getHinhbaihat()).into(holder.imghinh);

    }

    @Override
    public int getItemCount() {
        return arrayListbaihathot.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenBaiHat,txtCasi;
        ImageView imghinh,imgluotthich;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTenBaiHat = itemView.findViewById(R.id.textviewTenBaiHatHot);
            txtCasi = itemView.findViewById(R.id.textviewcasibaihathot);
            imghinh = itemView.findViewById(R.id.imageviewBaiHatHot);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthich);

            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataserver dataserver = APIServer.getServer();
                    Call<String> callback = dataserver.getUpdateLuotthich(arrayListbaihathot.get(getPosition()).getIdbaihat(),"1");
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String luotthich=response.body();
                            if(luotthich.equals("Success")){
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",arrayListbaihathot.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
