package dev.zero.tiplangpdam.service;

import java.util.ArrayList;
import java.util.Map;

import dev.zero.tiplangpdam.model.response.Login;
import dev.zero.tiplangpdam.model.response.PelangganResponse;
import dev.zero.tiplangpdam.model.response.PelanggaranResponse;
import dev.zero.tiplangpdam.model.response.RealisasiResponse;
import dev.zero.tiplangpdam.model.response.SPKResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public class ApiService {
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

        @POST("tiplang/api/add-realisasi")
        @FormUrlEncoded
        Call<RealisasiResponse> postForm(@PartMap Map<String, RequestBody> params, ArrayList<MultipartBody.Part> paramImage);
    }

    public interface GetService{
        @GET("tiplang/api/get-spk/{id}")
        Call<SPKResponse> getSPK(@Path("id") String id);

        @GET("tiplang/api/get-pelanggaran")
        Call<PelanggaranResponse> getPelanggaran();

        @GET("tiplang/api/get-pelanggan/{id}")
        Call<PelangganResponse> getPelanggan(@Path("id") String id);
    }
}
