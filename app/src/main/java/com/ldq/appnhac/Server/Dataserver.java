package com.ldq.appnhac.Server;

import com.ldq.appnhac.Model.Album;
import com.ldq.appnhac.Model.BaiHat;
import com.ldq.appnhac.Model.ChuDeTheLoai;
import com.ldq.appnhac.Model.Chude;
import com.ldq.appnhac.Model.HoSo;
import com.ldq.appnhac.Model.Playlist;
import com.ldq.appnhac.Model.Quangcao;
import com.ldq.appnhac.Model.TaiKhoan;
import com.ldq.appnhac.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataserver {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET ("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistcurrentDay();

    @GET ("chudevatheloai.php")
    Call<ChuDeTheLoai> GetChuDeTheLoai();

    @GET ("albumhot.php")
    Call<List<Album>> GetAlbum();


    @GET ("baihatduocyeuthich.php")
    Call<List<BaiHat>> GetBaiHatDuocYeuThich();

    //muốn sử dụng phương thúc _post lên phía server thì sử dụng từ khóa @FormUrlEncoded
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHat(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoPlaylist(@Field("idplaylist") String idplaylist);

    @GET ("danhsachcacplaylist.php")
    Call<List<Playlist>> getDanhSachCacPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<Chude>> getAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> getTheLoaiTheoChuDe(@Field("idchude")String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> getAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoAlbum(@Field("idalbum")String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> getUpdateLuotthich(@Field("idbaihat")String idbaihat, @Field("luotthich")String luotthich );

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> getTimKiem(@Field("tukhoa")String tukhoa);

    @FormUrlEncoded
    @POST("dangnhap.php")
    Call<List<TaiKhoan>> getTaiKhoan(@Field("tentaikhoan")String tentaikhoan);


    @FormUrlEncoded
    @POST("dangky.php")
    Call<String> getDangKyTaiKhoan(@Field("tentaikhoan")String tentaikhoan,@Field("matkhau")String matkhau);

    @FormUrlEncoded
    @POST("layhoso.php")
    Call<HoSo> getHoSo(@Field("idtaikhoan") String idtaikhoan);

    @FormUrlEncoded
    @POST("dangkyhoso.php")
    Call<String> getDangKyHoSo(@Field("idtaikhoan")String idtaikhoan);
}
