package com.ldq.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ldq.appnhac.Adapter.AdapterDanhSachTheLoaiTheChuDe;
import com.ldq.appnhac.Model.Chude;
import com.ldq.appnhac.Model.TheLoai;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {
    Chude chude;
    RecyclerView recyclerView;
    Toolbar toolbar;
    AdapterDanhSachTheLoaiTheChuDe adapterDanhSachTheLoaiTheChuDe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de);
        recyclerView = findViewById(R.id.recyclerviewTheloaitheochude);
        toolbar = findViewById(R.id.toolbartheloaitheochude);

        GetIntent();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(chude.getTenChuDe());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        GetData();
    }

    private void GetData() {
        Dataserver dataserver = APIServer.getServer();
        Call<List<TheLoai>> callback = dataserver.getTheLoaiTheoChuDe(chude.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> arraytheloai = (ArrayList<TheLoai>) response.body();
                adapterDanhSachTheLoaiTheChuDe =new AdapterDanhSachTheLoaiTheChuDe(DanhSachTheLoaiTheoChuDeActivity.this,arraytheloai);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoChuDeActivity.this,2));
                recyclerView.setAdapter(adapterDanhSachTheLoaiTheChuDe);

            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void GetIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("chude")){
            chude= (Chude) intent.getSerializableExtra("chude");
            Toast.makeText(this,chude.getTenChuDe(), Toast.LENGTH_SHORT).show();
        }
    }
}
