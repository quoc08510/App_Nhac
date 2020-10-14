package com.ldq.appnhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

@SerializedName("idalbum")
@Expose
private String idalbum;
@SerializedName("TenAlbum")
@Expose
private String tenAlbum;
@SerializedName("TenCaSiAlbum")
@Expose
private String tenCaSiAlbum;
@SerializedName("HinhAlbum")
@Expose
private String hinhAlbum;

public String getIdalbum() {
return idalbum;
}

public void setIdalbum(String idalbum) {
this.idalbum = idalbum;
}

public String getTenAlbum() {
return tenAlbum;
}

public void setTenAlbum(String tenAlbum) {
this.tenAlbum = tenAlbum;
}

public String getTenCaSiAlbum() {
return tenCaSiAlbum;
}

public void setTenCaSiAlbum(String tenCaSiAlbum) {
this.tenCaSiAlbum = tenCaSiAlbum;
}

public String getHinhAlbum() {
return hinhAlbum;
}

public void setHinhAlbum(String hinhAlbum) {
this.hinhAlbum = hinhAlbum;
}

}