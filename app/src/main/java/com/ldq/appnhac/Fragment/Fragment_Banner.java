package com.ldq.appnhac.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ldq.appnhac.Adapter.BannerAdapter;
import com.ldq.appnhac.Model.Quangcao;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;

    Runnable runnable;
    Handler handler;

    int currentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view =inflater.inflate(R.layout.fragment_banner,container,false);
        Anhxa();
        GetData();
        return view;
    }
    private void Anhxa(){
        viewPager = view.findViewById(R.id.viewpaper);
        circleIndicator =  view.findViewById(R.id.indiacatordefault);
    }
    private void GetData(){

        Dataserver dataserver = APIServer.getServer();
        Call<List<Quangcao>> callback = dataserver.GetDataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                //lắng nghe cho việc có kết quả trả về
                ArrayList<Quangcao> banners = (ArrayList<Quangcao>) response.body();
                bannerAdapter= new BannerAdapter(getActivity(),banners);

                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);


                handler= new Handler();


                runnable= new Runnable() {
                    @Override
                    public void run() {
                        currentItem= viewPager.getCurrentItem();
                        currentItem++;
                        if  (currentItem>=viewPager.getAdapter().getCount()){
                            currentItem=0;
                        }
                        viewPager.setCurrentItem(currentItem,true);
                        handler.postDelayed(runnable,5500);
                    }
                };
                handler.postDelayed(runnable,5500);
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {
                //lắng nghe cho việc thất bại
            }
        });

    }
}
