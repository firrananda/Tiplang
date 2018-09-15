package dev.zero.tiplangpdam.service;

import java.util.ArrayList;
import java.util.Map;

import dev.zero.tiplangpdam.model.List_Realisasi;
import dev.zero.tiplangpdam.model.PelangganRevKirim;
import dev.zero.tiplangpdam.model.response.CountBaruResponse;
import dev.zero.tiplangpdam.model.response.CountRevisiResponse;
import dev.zero.tiplangpdam.model.response.List_RealisasiResponse;
import dev.zero.tiplangpdam.model.response.Login;
import dev.zero.tiplangpdam.model.response.PelangganResponse;
import dev.zero.tiplangpdam.model.response.PelangganRevKirimResponse;
import dev.zero.tiplangpdam.model.response.PelangganRevResponse;
import dev.zero.tiplangpdam.model.response.PelanggaranResponse;
import dev.zero.tiplangpdam.model.response.RealisasiResponse;
import dev.zero.tiplangpdam.model.response.SPKResponse;
import dev.zero.tiplangpdam.model.response.Update_RealisasiResponse;
import dev.zero.tiplangpdam.model.response.ViewRealRevBaruResponse;
import dev.zero.tiplangpdam.model.response.ViewRealRevKirimResponse;
import dev.zero.tiplangpdam.model.response.View_RealisasiResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public class ApiService {
    //public static String BASE_URL = "http://222.124.168.221";
    public static String BASE_URL = "http://192.168.43.21";

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

        @Multipart
        @POST("tiplang/api/add-realisasi")
        Call<RealisasiResponse> postForm(@PartMap Map<String, RequestBody> params,
                                         @Part MultipartBody.Part pict1,
                                         @Part MultipartBody.Part pict2,
                                         @Part MultipartBody.Part pict3,
                                         @Part MultipartBody.Part pict4);

        @Multipart
        @POST("tiplang/api/update-realisasi/{id}")
        Call<Update_RealisasiResponse> updateForm (@Path("id") String id,
                                                   @PartMap Map<String, RequestBody> params);

    }

    public interface GetService{
        @GET("tiplang/api/get-spk/{id}")
        Call<SPKResponse> getSPK(@Path("id") String id);

        @GET("tiplang/api/get-pelanggaran")
        Call<PelanggaranResponse> getPelanggaran();

        @GET("tiplang/api/get-pelanggan/{id}")
        Call<PelangganResponse> getPelanggan(@Path("id") String id);

        @GET("tiplang/api/count-baru/{id}")
        Call<CountBaruResponse> getCountBaru();

        @GET("tiplang/api/count-revisi/{id}")
        Call<CountRevisiResponse> getCountRevisi();

        @GET("tiplang/api/list-realisasi/{id}")
        Call<List_RealisasiResponse> getListRealisasi(@Path("id")String id);

        @GET("tiplang/api/view-realisasi/{id}")
        Call<View_RealisasiResponse> getViewRealisasi(@Path("id")String id);

        @GET("tiplang/api/list-revisi-baru/{id}")
        Call<PelangganRevResponse> getPelangganRev(@Path("id") String id);

        @GET("tiplang/api/view-realisasi-revisi-baru/{id}")
        Call<ViewRealRevBaruResponse> getViewRealRevBaru(@Path("id") String id);

        @GET("tiplang/api/list-revisi-kirim/{id}")
        Call<PelangganRevKirimResponse> getPelangganRevKirim(@Path("id") String id);

        @GET("tiplang/api/view-realisasi-revisi-kirim/{id}")
        Call<ViewRealRevKirimResponse> getViewRealRrevKirim(@Path("id") String id);
    }
}
