package dev.zero.tiplangpdam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.activity.baru.KirimActivity;
import dev.zero.tiplangpdam.activity.baru.ProsesActivity;
import dev.zero.tiplangpdam.activity.baru.TerimaActivity;
import dev.zero.tiplangpdam.helper.FormDataSaveHelper;
import dev.zero.tiplangpdam.model.response.CountBaruResponse;
import dev.zero.tiplangpdam.model.response.List_RealisasiResponse;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaruFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.tv_countterima)
    TextView tvCountterima;
    @BindView(R.id.tv_countproses)
    TextView tvCountproses;
    @BindView(R.id.tv_countkirim)
    TextView tvCountkirim;
    SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baru, container, false);
        unbinder = ButterKnife.bind(this, view);

        sessionManager = new SessionManager(getContext());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        //getCount();
    }

    @OnClick({R.id.cv_terima, R.id.cv_proses, R.id.cv_kirim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_terima:
                getContext().startActivity(new Intent(getContext(), TerimaActivity.class));
                break;
            case R.id.cv_proses:
                getContext().startActivity(new Intent(getContext(), ProsesActivity.class));
                break;
            case R.id.cv_kirim:
                getContext().startActivity(new Intent(getContext(), KirimActivity.class));
                break;
        }
    }

//    public void getCount() {
//        // Get Count Terima
//        ApiService.service_get.getCountBaru().enqueue(new Callback<CountBaruResponse>() {
//            @Override
//            public void onResponse(Call<CountBaruResponse> call, Response<CountBaruResponse> response) {
//                if (response.code() == 200){
//                    if (response.body().getCode() == 302){
//                        tvCountterima.setText(response.body().getBaru());
//                    }
//                    else {
//                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                else {
////                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CountBaruResponse> call, Throwable t) {
//
//            }
//        });
//
//        // Get Count proses
//        tvCountproses.setText(String.valueOf(FormDataSaveHelper.getData().size()));
//
//        // Get Count Kirim
//        ApiService.service_get.getListRealisasi(sessionManager.getKeyId()).enqueue(new Callback<List_RealisasiResponse>() {
//            @Override
//            public void onResponse(Call<List_RealisasiResponse> call, Response<List_RealisasiResponse> response) {
//                if (response.code() == 200){
//                    if (response.body().getCode() == 302){
//                        tvCountkirim.setText(String.valueOf(response.body().getList().size()));
//                    }else{
//                        Toast.makeText(getContext(), "Error " + response.body().getCode() + " : " + response.body().getMessaage(), Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(getContext(), "Error " + response.code() + " : " + response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List_RealisasiResponse> call, Throwable t) {
//                Toast.makeText(getContext(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                call.cancel();
//            }
//        });
//    }
}
