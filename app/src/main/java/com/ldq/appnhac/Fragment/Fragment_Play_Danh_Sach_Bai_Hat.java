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

import com.ldq.appnhac.Activity.PlayNhacActivity;
import com.ldq.appnhac.Adapter.AdapterPlayNhac;
import com.ldq.appnhac.R;

public class Fragment_Play_Danh_Sach_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerViewplaybaihat;
    AdapterPlayNhac adapterPlayNhac;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.fragment_play_danh_sach_bai_hat,container,false);
       recyclerViewplaybaihat = view.findViewById(R.id.recyclerviewPlaybaihat);
       if (PlayNhacActivity.arrayBaihat.size()>0) {
           adapterPlayNhac = new AdapterPlayNhac(getActivity(), PlayNhacActivity.arrayBaihat);
           recyclerViewplaybaihat.setLayoutManager(new LinearLayoutManager(getActivity()));
           recyclerViewplaybaihat.setAdapter(adapterPlayNhac);
       }
       return view;
    }

    public static void playnhactheoposition(int positon){


    }

}
