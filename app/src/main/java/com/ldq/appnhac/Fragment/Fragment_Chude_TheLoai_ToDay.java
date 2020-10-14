package com.ldq.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.ldq.appnhac.Activity.DanhSachBaiHatActivity;
import com.ldq.appnhac.Activity.DanhSachTatCaChuDeActivity;
import com.ldq.appnhac.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.ldq.appnhac.Model.ChuDeTheLoai;
import com.ldq.appnhac.Model.Chude;
import com.ldq.appnhac.Model.TheLoai;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Chude_TheLoai_ToDay extends Fragment {
    View view;
    ChuDeTheLoai chuDeTheLoai;

    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_chude_theloai_today,container,false);

        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtxemthemchudetheloai= view.findViewById(R.id.textviewxemthem);

        txtxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), DanhSachTatCaChuDeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData(){
        Dataserver dataserver= APIServer.getServer();
        Call<ChuDeTheLoai> callback =dataserver.GetChuDeTheLoai();
        callback.enqueue(new Callback<ChuDeTheLoai>() {
            @Override
            public void onResponse(Call<ChuDeTheLoai> call, Response<ChuDeTheLoai> response) {
                chuDeTheLoai = response.body();

                final ArrayList<Chude> chuDeArrayList= new ArrayList<>();
                chuDeArrayList.addAll(chuDeTheLoai.getChude());

                final ArrayList<TheLoai> theloaiArrayList= new ArrayList<>();
                theloaiArrayList.addAll(chuDeTheLoai.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());

                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580,250);
                layout.setMargins(10,20,10,30);
                for(int i=0; i<(chuDeArrayList.size());i++){
                    CardView cardView= new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    if(chuDeArrayList.get(i).getHinhChuDe()!=null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachTheLoaiTheoChuDeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for(int j=0; j<(theloaiArrayList.size());j++){
                    CardView cardView= new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    if(theloaiArrayList.get(j).getHinhTheLoai()!=null){
                        Picasso.with(getActivity()).load(theloaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(getActivity(), DanhSachBaiHatActivity.class);
                            intent.putExtra("idtheloai",theloaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChuDeTheLoai> call, Throwable t) {

            }
        });
    }
}
