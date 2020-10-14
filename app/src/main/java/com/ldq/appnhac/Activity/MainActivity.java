package com.ldq.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.ldq.appnhac.Adapter.MainViewPagerAdapter;
import com.ldq.appnhac.Fragment.Fragment_Tim_Kiem;
import com.ldq.appnhac.Fragment.Fragment_Trang_Chu;
import com.ldq.appnhac.Model.TaiKhoan;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btndangnhap,btndangky;
    EditText edttentaikhoan,edtmatkhau;
    CheckBox cbghinho;
    public static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        Intent intent1 = getIntent();
        if (intent1.hasExtra("taikhoanmatkhau")){
            edttentaikhoan.setText(intent1.getStringArrayListExtra("taikhoanmatkhau").get(0));
            edtmatkhau.setText(intent1.getStringArrayListExtra("taikhoanmatkhau").get(1));
        }
        if(cbghinho.isChecked()){
            Intent intent = new Intent(MainActivity.this, TrangChinhActivity.class);
            startActivity(intent);
        }



        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentaikhoan = edttentaikhoan.getText().toString();
                Dataserver dataserver = APIServer.getServer();
                Call<List<TaiKhoan>> callback = dataserver.getTaiKhoan(tentaikhoan);
                callback.enqueue(new Callback<List<TaiKhoan>>() {
                    @Override
                    public void onResponse(Call<List<TaiKhoan>> call, Response<List<TaiKhoan>> response) {
                        ArrayList<TaiKhoan> taiKhoan = (ArrayList<TaiKhoan>) response.body();
                        if (taiKhoan.size()>0){
                            String matkhau = edtmatkhau.getText().toString();
                            if (matkhau.equals(taiKhoan.get(0).getMatkhau())){
                                if (cbghinho.isChecked()) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("taikhoan",edttentaikhoan.getText().toString().trim());
                                    editor.putString("matkhau",edtmatkhau.getText().toString().trim());
                                    editor.putBoolean("checked",true);
                                    editor.commit();
                                }else{
                                    SharedPreferences.Editor editer = sharedPreferences.edit();
                                    editer.remove("taikhoan");
                                    editer.remove("matkhau");
                                    editer.remove("checked");
                                    editer.commit();
                                }
                                Intent intent = new Intent(MainActivity.this, TrangChinhActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Tài khoản không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<TaiKhoan>> call, Throwable t) {
                    }
                });
            }
        });
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Dang_Ky_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        btndangky = findViewById(R.id.buttondangky);
        btndangnhap = findViewById(R.id.buttondangnhap);
        edttentaikhoan = findViewById(R.id.edittexttendangnhap);
        edtmatkhau = findViewById(R.id.edittextmatkhau);
        cbghinho=findViewById(R.id.checkboxghinho);

        //lay database cua dang nhap
        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        edttentaikhoan.setText(sharedPreferences.getString("taikhoan",""));
        edtmatkhau.setText(sharedPreferences.getString("matkhau",""));
        cbghinho.setChecked(sharedPreferences.getBoolean("checked",false));

    }
}
