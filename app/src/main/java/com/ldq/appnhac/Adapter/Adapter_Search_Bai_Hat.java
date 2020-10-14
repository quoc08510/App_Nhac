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

import okhttp3.internal.cache.CacheInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Search_Bai_Hat extends RecyclerView.Adapter<Adapter_Search_Bai_Hat.ViewHolder>{
    Context context;
    ArrayList<BaiHat> arraysearchbaihat;
    View view;
    public Adapter_Search_Bai_Hat(Context context, ArrayList<BaiHat> arraysearchbaihat) {
        this.context = context;
        this.arraysearchbaihat = arraysearchbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_search_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = arraysearchbaihat.get(position);
        holder.txttencasi.setText(baiHat.getCasi());
        holder.txttenbaihat.setText(baiHat.getTenbaihat());
        Picasso.with(context).load(baiHat.getLinkbaihat()).into(holder.imgbaihat);
    }

    @Override
    public int getItemCount() {
        return arraysearchbaihat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenbaihat,txttencasi;
        ImageView imgbaihat,imgluotthich;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txttenbaihat=itemView.findViewById(R.id.textviewsearchtenbaihat);
            txttencasi=itemView.findViewById(R.id.textviewsearchtencasi);
            imgbaihat = itemView.findViewById(R.id.imageviewSearchbaihat);
            imgluotthich=itemView.findViewById(R.id.imageviewsearchluotthich);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",arraysearchbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataserver dataserver = APIServer.getServer();
                    Call<String> callback = dataserver.getUpdateLuotthich(arraysearchbaihat.get(getPosition()).getIdbaihat(),"1");
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua= response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
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
        }
    }
}
