package com.ldq.appnhac.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ldq.appnhac.Activity.MainActivity;
import com.ldq.appnhac.Activity.TrangChinhActivity;
import com.ldq.appnhac.Model.HoSo;
import com.ldq.appnhac.Model.TaiKhoan;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Ten_Ho_So extends Fragment {
    View view;
    TextView txtTenHoSo,txtLuotThich,txtdangxuat,txthotenhoso;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ten_ho_so,container,false);
        txtTenHoSo = view.findViewById(R.id.textviewtenhoso);
        txtLuotThich = view.findViewById(R.id.textviewluotthichhoso);
        txtdangxuat =view.findViewById(R.id.textviewdangxuat);
        txthotenhoso = view.findViewById(R.id.textviewhotenhoso);
        TaiKhoan taikhoan = TrangChinhActivity.taikhoan;

        txtTenHoSo.setText(taikhoan.getTentaikhoan());

        Dataserver dataserver = APIServer.getServer();
        Call<HoSo> callback = dataserver.getHoSo(taikhoan.getIdtaikhoan());
        callback.enqueue(new Callback<HoSo>() {
            @Override
            public void onResponse(Call<HoSo> call, Response<HoSo> response) {
                HoSo hoSo = response.body();
                txtLuotThich.setText(hoSo.getLuotThich());
                txthotenhoso.setText(hoSo.getHoTen());
            }

            @Override
            public void onFailure(Call<HoSo> call, Throwable t) {

            }
        });
        txtdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                SharedPreferences.Editor editer = MainActivity.sharedPreferences.edit();
                editer.remove("matkhau");
                editer.remove("checked");
                editer.commit();
                startActivity(intent);

            }
        });

        return view;
    }
}
