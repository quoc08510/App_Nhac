package com.ldq.appnhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HoSo {

@SerializedName("IdHoSo")
@Expose
private String idHoSo;
@SerializedName("IdTaiKhoan")
@Expose
private String idTaiKhoan;
@SerializedName("HoTen")
@Expose
private String hoTen;
@SerializedName("NamSinh")
@Expose
private String namSinh;
@SerializedName("DiaChi")
@Expose
private String diaChi;
@SerializedName("SoThich")
@Expose
private String soThich;
@SerializedName("LuotThich")
@Expose
private String luotThich;

public String getIdHoSo() {
return idHoSo;
}

public void setIdHoSo(String idHoSo) {
this.idHoSo = idHoSo;
}

public String getIdTaiKhoan() {
return idTaiKhoan;
}

public void setIdTaiKhoan(String idTaiKhoan) {
this.idTaiKhoan = idTaiKhoan;
}

public String getHoTen() {
return hoTen;
}

public void setHoTen(String hoTen) {
this.hoTen = hoTen;
}

public String getNamSinh() {
return namSinh;
}

public void setNamSinh(String namSinh) {
this.namSinh = namSinh;
}

public String getDiaChi() {
return diaChi;
}

public void setDiaChi(String diaChi) {
this.diaChi = diaChi;
}

public String getSoThich() {
return soThich;
}

public void setSoThich(String soThich) {
this.soThich = soThich;
}

public String getLuotThich() {
return luotThich;
}

public void setLuotThich(String luotThich) {
this.luotThich = luotThich;
}

}