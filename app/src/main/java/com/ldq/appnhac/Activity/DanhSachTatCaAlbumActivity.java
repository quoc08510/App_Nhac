package com.ldq.appnhac.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ldq.appnhac.Adapter.AdapterAllAlbum;
import com.ldq.appnhac.Model.Album;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatCaAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewallalbum;
    Toolbar toolbarAllAlbum;
    AdapterAllAlbum adapterAllAlbum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_album);
        recyclerViewallalbum =findViewById(R.id.recyclerviewAllAlbum);
        toolbarAllAlbum = findViewById(R.id.toolbarallalbum);
        setSupportActionBar(toolbarAllAlbum);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbarAllAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        GetData();
    }

    private void GetData() {
        Dataserver dataserver = APIServer.getServer();
        Call<List<Album>> callback = dataserver.getAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrayAlbum = (ArrayList<Album>) response.body();
                adapterAllAlbum = new AdapterAllAlbum(DanhSachTatCaAlbumActivity.this,arrayAlbum);
                recyclerViewallalbum.setLayoutManager(new GridLayoutManager(DanhSachTatCaAlbumActivity.this,2));
                recyclerViewallalbum.setAdapter(adapterAllAlbum);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
