package com.ldq.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.ldq.appnhac.Adapter.AdapterDanhSachAllChuDe;
import com.ldq.appnhac.Model.Chude;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatCaChuDeActivity extends AppCompatActivity {
    RecyclerView recyclerViewallchude;
    Toolbar toolbarallchude;

    AdapterDanhSachAllChuDe adapterDanhSachAllChuDe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_chu_de);
        AnhXa();
        GetDaTa();
    }

    private void GetDaTa() {
        Dataserver dataserver= APIServer.getServer();
        Call<List<Chude>> callback =dataserver.getAllChuDe();
        callback.enqueue(new Callback<List<Chude>>() {
            @Override
            public void onResponse(Call<List<Chude>> call, Response<List<Chude>> response) {
                ArrayList<Chude> arrayListchude= (ArrayList<Chude>) response.body();
                adapterDanhSachAllChuDe = new AdapterDanhSachAllChuDe(DanhSachTatCaChuDeActivity.this,arrayListchude);
                recyclerViewallchude.setLayoutManager(new GridLayoutManager(DanhSachTatCaChuDeActivity.this,1));
                recyclerViewallchude.setAdapter(adapterDanhSachAllChuDe);
            }

            @Override
            public void onFailure(Call<List<Chude>> call, Throwable t) {

            }
        });

    }


    private void AnhXa() {
        recyclerViewallchude=findViewById(R.id.recyclerviewallchude);
        toolbarallchude=findViewById(R.id.toolbatallchude);

        setSupportActionBar(toolbarallchude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        toolbarallchude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
