package com.ldq.appnhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaiKhoan {

@SerializedName("Idtaikhoan")
@Expose
private String idtaikhoan;
@SerializedName("Tentaikhoan")
@Expose
private String tentaikhoan;
@SerializedName("Matkhau")
@Expose
private String matkhau;

public String getIdtaikhoan() {
return idtaikhoan;
}

public void setIdtaikhoan(String idtaikhoan) {
this.idtaikhoan = idtaikhoan;
}

public String getTentaikhoan() {
return tentaikhoan;
}

public void setTentaikhoan(String tentaikhoan) {
this.tentaikhoan = tentaikhoan;
}

public String getMatkhau() {
return matkhau;
}

public void setMatkhau(String matkhau) {
this.matkhau = matkhau;
}

}