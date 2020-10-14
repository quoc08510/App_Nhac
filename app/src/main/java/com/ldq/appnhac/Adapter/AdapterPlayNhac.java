package com.ldq.appnhac.Adapter;

import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldq.appnhac.Activity.PlayNhacActivity;
import com.ldq.appnhac.Fragment.Fragment_Play_Danh_Sach_Bai_Hat;
import com.ldq.appnhac.Model.BaiHat;
import com.ldq.appnhac.R;

import java.util.ArrayList;

import static com.ldq.appnhac.Activity.PlayNhacActivity.arrayBaihat;

public class AdapterPlayNhac extends RecyclerView.Adapter<AdapterPlayNhac.ViewHolder> {
    Context context;
    ArrayList <BaiHat> arraybaihat;
    public static int positionbaihat;

    public AdapterPlayNhac(Context context, ArrayList<BaiHat> arraybaihat) {
        this.context = context;
        this.arraybaihat = arraybaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final BaiHat baiHat = arraybaihat.get(position);
        holder.txtTenBaiHat.setText(baiHat.getTenbaihat());
        holder.txtTenCaSi.setText(baiHat.getCasi());
        holder.txtIndex.setText(position+1+"");

    }

    @Override
    public int getItemCount() {
        return arraybaihat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenBaiHat,txtTenCaSi,txtIndex;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.textviewplaynhacindex);
            txtTenBaiHat = itemView.findViewById(R.id.textviewplaynhactenbaihat);
            txtTenCaSi = itemView.findViewById(R.id.textviewplaynhactencasi);
        }
    }
}
