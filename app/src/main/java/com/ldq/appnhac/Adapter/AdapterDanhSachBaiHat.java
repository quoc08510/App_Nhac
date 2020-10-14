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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDanhSachBaiHat extends RecyclerView.Adapter<AdapterDanhSachBaiHat.ViewHolder>{
    Context context;
    ArrayList<BaiHat> arrayBaiHat;

    public AdapterDanhSachBaiHat(Context context, ArrayList<BaiHat> arrayBaiHat) {
        this.context = context;
        this.arrayBaiHat = arrayBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baihat =arrayBaiHat.get(position);
        holder.txtTenCaSi.setText(baihat.getCasi());
        holder.txtTenBaiHat.setText(baihat.getTenbaihat());
        holder.txtIndex.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return arrayBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLuotthichdanhsach;
        TextView txtIndex,txtTenBaiHat,txtTenCaSi;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtTenCaSi= itemView.findViewById(R.id.textviewTenCaSi);
            txtTenBaiHat = itemView.findViewById(R.id.textviewTenBaiHat);
            txtIndex =itemView.findViewById(R.id.textviewDanhSachIndex);
            imgLuotthichdanhsach = itemView.findViewById(R.id.imageviewLuotThichDanhSachBaiHat);

            imgLuotthichdanhsach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLuotthichdanhsach.setImageResource(R.drawable.iconloved);
                    Dataserver dataserver = APIServer.getServer();
                    Call<String> callback =dataserver.getUpdateLuotthich(arrayBaiHat.get(getPosition()).getIdbaihat(),"1");
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String luotthich = response.body();
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
                    imgLuotthichdanhsach.setEnabled(false);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",arrayBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }
}
