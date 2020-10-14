package com.ldq.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ldq.appnhac.Adapter.AdapterDanhSachCacPlayList;
import com.ldq.appnhac.Model.Playlist;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlayListActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachplaylist;
    AdapterDanhSachCacPlayList adapterDanhSachCacPlayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_play_list);
        AnhXa();
        init();
        GetData();
    }

    private void GetData() {
        Dataserver dataserver= APIServer.getServer();
        Call<List<Playlist>> callback = dataserver.getDanhSachCacPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrayPlaylist = (ArrayList<Playlist>) response.body();
                adapterDanhSachCacPlayList = new AdapterDanhSachCacPlayList(DanhSachPlayListActivity.this,arrayPlaylist);
                recyclerViewdanhsachplaylist.setLayoutManager(new GridLayoutManager(DanhSachPlayListActivity.this,2));
                recyclerViewdanhsachplaylist.setAdapter(adapterDanhSachCacPlayList);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbar= findViewById(R.id.toolbarDanhSachCacPlaylist);
        recyclerViewdanhsachplaylist=findViewById(R.id.recyclerViewdanhsachplaylist);
    }
}
