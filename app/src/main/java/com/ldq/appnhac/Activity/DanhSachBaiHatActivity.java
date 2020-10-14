package com.ldq.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ldq.appnhac.Adapter.AdapterDanhSachBaiHat;
import com.ldq.appnhac.Model.Album;
import com.ldq.appnhac.Model.BaiHat;
import com.ldq.appnhac.Model.Playlist;
import com.ldq.appnhac.Model.Quangcao;
import com.ldq.appnhac.Model.TheLoai;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    ArrayList<BaiHat> arrayBaiHat;
    Quangcao quangcao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    ImageView imgDanhsachCaKhuc;
    AdapterDanhSachBaiHat adapterDanhSachBaiHat;
    Playlist playlist;
    TheLoai theloai;
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        AnhXa();
        init();
        if(quangcao!=null&& !quangcao.getTenBaiHat().equals("")){
            setValueInview(quangcao.getTenBaiHat(),quangcao.getHinhBaiHat());
            GetDataQuangCao(quangcao.getIdQuangCao());
        }

        if(playlist!=null&&!playlist.getTen().equals("")){
            setValueInview(playlist.getTen(),playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdplaylist());
        }

        if(theloai!=null&&!theloai.getTenTheLoai().equals("")){
            setValueInview(theloai.getTenTheLoai(),theloai.getHinhTheLoai());
            GetDataTheLoai(theloai.getIdTheLoai());
        }

        if(album!=null&&!album.getTenAlbum().equals("")){
            setValueInview(album.getTenAlbum(),album.getHinhAlbum());
            GetDataAlbum(album.getIdalbum());
        }
    }

    private void GetDataAlbum(String idalbum) {
        Dataserver dataserver = APIServer.getServer();
        Call<List<BaiHat>> callback = dataserver.getDanhSachBaiHatTheoAlbum(idalbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayBaiHat = (ArrayList<BaiHat>) response.body();
                adapterDanhSachBaiHat = new AdapterDanhSachBaiHat(DanhSachBaiHatActivity.this,arrayBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(adapterDanhSachBaiHat);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idTheLoai) {
        Dataserver dataserver=APIServer.getServer();
        Call<List<BaiHat>> callback = dataserver.getDanhSachBaiHatTheoTheLoai(idTheLoai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayBaiHat = (ArrayList<BaiHat>) response.body();
                adapterDanhSachBaiHat = new AdapterDanhSachBaiHat(DanhSachBaiHatActivity.this,arrayBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(adapterDanhSachBaiHat);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idplaylist) {
        Dataserver dataserver = APIServer.getServer();
        Call<List<BaiHat>> callback= dataserver.getDanhSachBaiHatTheoPlaylist(idplaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayBaiHat= (ArrayList<BaiHat>) response.body();
                adapterDanhSachBaiHat = new AdapterDanhSachBaiHat(DanhSachBaiHatActivity.this,arrayBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(adapterDanhSachBaiHat);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangCao(String idquangcao) {
        Dataserver dataserver = APIServer.getServer();

        Call<List<BaiHat>> callback = dataserver.getDanhSachBaiHat(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayBaiHat = (ArrayList<BaiHat>) response.body();
                adapterDanhSachBaiHat = new AdapterDanhSachBaiHat(DanhSachBaiHatActivity.this,arrayBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(adapterDanhSachBaiHat);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInview(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url= new URL(hinh);
            Bitmap bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgDanhsachCaKhuc);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        floatingActionButton.setEnabled(false);
    }

    public void DataIntent(){
        Intent intent= getIntent();
        if(intent!=null){
            if(intent.hasExtra("banner")){
                quangcao= (Quangcao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemplaylist")){
                playlist= (Playlist) intent.getSerializableExtra("itemplaylist");
            }

            if(intent.hasExtra("idtheloai")){
                theloai=(TheLoai) intent.getSerializableExtra("idtheloai");
            }

            if(intent.hasExtra("idalbum")){
                album= (Album) intent.getSerializableExtra("idalbum");
            }
        }
    }
    public void AnhXa(){
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        toolbar = findViewById(R.id.toolbarDanhSach);
        recyclerViewDanhSachBaiHat = findViewById(R.id.recycleViewDanhSachBaihat);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        imgDanhsachCaKhuc = findViewById(R.id.imageviewdanhsachcakhuc);
    }

    public void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this,PlayNhacActivity.class);
                intent.putExtra("cacbaihat",arrayBaiHat);
                startActivity(intent);
            }
        });
    }
}
