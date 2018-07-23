package dev.zero.tiplangpdam.service;

import dev.zero.tiplangpdam.model.BATD;
import dev.zero.tiplangpdam.model.Pelanggaran;
import dev.zero.tiplangpdam.model.SPK;
import dev.zero.tiplangpdam.model.response.BATDResponse;
import dev.zero.tiplangpdam.model.response.Form;
import dev.zero.tiplangpdam.model.response.Login;
import dev.zero.tiplangpdam.model.response.PelanggaranResponse;
import dev.zero.tiplangpdam.model.response.RealisasiResponse;
import dev.zero.tiplangpdam.model.response.SPKResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ApiService {
    public static String BASE_URL = "http://222.124.168.221";

    public static PostService service_post = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.PostService.class);

    public static GetService service_get = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.GetService.class);

    public interface PostService{
        @POST("tiplang/api/login")
        @FormUrlEncoded
        Call<Login> postLogin(@Field("nip") String nip, @Field("password") String password);

        @POST("tiplang/api/add-realisasi")
        @FormUrlEncoded
        Call<RealisasiResponse> postForm(@Field("kondisi_stan_meter") String kondisi_stan_meter,
                                         @Field("catatan_stan_meter") String catatan_stan_meter,
                                         @Field("tanggal_angkat") String tanggal_angkat,
                                         @Field("no_meter") String no_meter,
                                         @Field("ukuran_meter") String ukuran_meter,
                                         @Field("angka_angkat") String angka_angkat,
                                         @Field("merk_meter") String merk_meter,
                                         @Field("batd_id") String batd_id,
                                         @Field("pelanggaran_id") String pelanggaran_id,
                                         @Field("hasil") String hasil,
                                         @Field("tanggal_realisasi") String tanggal_realisasi);
    }

    public interface GetService{
        @GET("tiplang/api/get-spk-by-petugas/{id}")
        Call<SPKResponse> getSPK(@Path("id") String id);

        @GET("tiplang/api/get-batd-by-spk/{id}")
        Call<BATDResponse> getBATD(@Path("id") String id);

        @GET("tiplang/api/get-pelanggaran")
        Call<PelanggaranResponse> getPelanggaran();
    }
}
