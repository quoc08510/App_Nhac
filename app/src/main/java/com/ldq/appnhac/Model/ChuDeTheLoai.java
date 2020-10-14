package com.ldq.appnhac.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChuDeTheLoai {

@SerializedName("TheLoai")
@Expose
private List<TheLoai> theLoai = null;
@SerializedName("chude")
@Expose
private List<Chude> chude = null;

public List<TheLoai> getTheLoai() {
return theLoai;
}

public void setTheLoai(List<TheLoai> theLoai) {
this.theLoai = theLoai;
}

public List<Chude> getChude() {
return chude;
}

public void setChude(List<Chude> chude) {
this.chude = chude;
}

}