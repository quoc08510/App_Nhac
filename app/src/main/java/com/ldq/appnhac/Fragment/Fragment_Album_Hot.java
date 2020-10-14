package com.ldq.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ldq.appnhac.Activity.DanhSachTatCaAlbumActivity;
import com.ldq.appnhac.Adapter.AdapterAlbum;
import com.ldq.appnhac.Model.Album;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewAlbum;
    TextView txtXemthemAlbum;
    AdapterAlbum adapterAlbum;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_hot,container,false);
        //anh xa
        recyclerViewAlbum = view.findViewById(R.id.recycleListviewalbum);
        txtXemthemAlbum = view.findViewById(R.id.textviewxemthemAlbum);
        txtXemthemAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), DanhSachTatCaAlbumActivity.class);
                startActivity(intent);
            }
        });
        getData();
        return view;
    }
    public void getData(){
        Dataserver dataserver = APIServer.getServer();
        Call<List<Album>> callback= dataserver.GetAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                adapterAlbum = new AdapterAlbum(getActivity(),albumArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewAlbum.setLayoutManager(linearLayoutManager);
                recyclerViewAlbum.setAdapter(adapterAlbum);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
