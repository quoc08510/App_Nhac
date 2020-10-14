package com.ldq.appnhac.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ldq.appnhac.Adapter.AdapterBaiHatHot;
import com.ldq.appnhac.Model.BaiHat;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_Duoc_Yeu_Thich extends Fragment {
    View view;
    RecyclerView recycleViewbaiHatHot;
    AdapterBaiHatHot adapterBaiHatHot;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view =inflater.inflate(R.layout.fragment_bai_hat_duoc_yeu_thich,container,false);
      recycleViewbaiHatHot = view.findViewById(R.id.recycleViewBaiHatHot);

      GetDaTa();
      return view;
    }

    public void GetDaTa(){
        Dataserver dataserver = APIServer.getServer();
        Call<List<BaiHat>> callback = dataserver.GetBaiHatDuocYeuThich();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> arrayBaiHatDuocYeuThiches = (ArrayList<BaiHat>) response.body();
                adapterBaiHatHot = new AdapterBaiHatHot(getActivity(),arrayBaiHatDuocYeuThiches);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycleViewbaiHatHot.setLayoutManager(linearLayoutManager);
                recycleViewbaiHatHot.setAdapter(adapterBaiHatHot);

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
            }
        });
    }
}
