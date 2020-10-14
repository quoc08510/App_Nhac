package com.ldq.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ldq.appnhac.Model.TaiKhoan;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dang_Ky_Activity extends AppCompatActivity {
    EditText edtTenDangKy,edtmatkhau1,edtmatkhau2;
    Button btndangky;
    ArrayList<String> arrayListtaikhoan = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang__ky_);
        AnhXa();

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentaikhoan = edtTenDangKy.getText().toString();
                final Dataserver dataserver = APIServer.getServer();
                Call<List<TaiKhoan>> callback = dataserver.getTaiKhoan(tentaikhoan);
                callback.enqueue(new Callback<List<TaiKhoan>>() {
                    @Override
                    public void onResponse(Call<List<TaiKhoan>> call, Response<List<TaiKhoan>> response) {
                        ArrayList<TaiKhoan> taiKhoan = (ArrayList<TaiKhoan>) response.body();
                        if (taiKhoan.size()==0){
                            if (edtmatkhau1.getText().toString().equals(edtmatkhau2.getText().toString())){
                                Dataserver dataserver1 =APIServer.getServer();
                                Call<String> callback = dataserver.getDangKyTaiKhoan(edtTenDangKy.getText().toString(),edtmatkhau1.getText().toString());
                                callback.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        String ketqua = response.body();
                                        if (ketqua.equals("success")){
                                            arrayListtaikhoan.clear();
                                            Toast.makeText(Dang_Ky_Activity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Dang_Ky_Activity.this,MainActivity.class);
                                            arrayListtaikhoan.add(edtTenDangKy.getText().toString());
                                            arrayListtaikhoan.add(edtmatkhau1.getText().toString());
                                            intent.putExtra("taikhoanmatkhau",arrayListtaikhoan);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(Dang_Ky_Activity.this, "Lỗi không xác định", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                    }
                                });
                            }else{
                                Toast.makeText(Dang_Ky_Activity.this,"Mật khẩu sai", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(Dang_Ky_Activity.this, "Đã có tài khoản này", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<TaiKhoan>> call, Throwable t) {

                    }
                });
            }
        });

    }

    private void AnhXa() {
        edtmatkhau1= findViewById(R.id.editTextmatkhau1);
        edtmatkhau2 = findViewById(R.id.editTextmatkhau2);
        edtTenDangKy = findViewById(R.id.editTexttendangky);
        btndangky = findViewById(R.id.buttonDangky);
    }
}
