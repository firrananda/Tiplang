package dev.zero.tiplangpdam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import dev.zero.tiplangpdam.activity.revisi.RevKirimActivity;
import dev.zero.tiplangpdam.activity.revisi.RevProsesActivity;
import dev.zero.tiplangpdam.activity.revisi.RevTerimaActivity;
import dev.zero.tiplangpdam.helper.FormDataRevSaveHelper;
import dev.zero.tiplangpdam.model.response.PelangganRevKirimResponse;
import dev.zero.tiplangpdam.model.response.PelangganRevResponse;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevisiFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.tv_countterima)
    TextView tvCountterima;
    @BindView(R.id.tv_countproses)
    TextView tvCountproses;
    @BindView(R.id.tv_countkirim)
    TextView tvCountkirim;
    SessionManager sessionManager;

    public RevisiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_revisi, container, false);
        unbinder = ButterKnife.bind(this, view);

        sessionManager = new SessionManager(getContext());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void onResume (){
        super.onResume();
        getCount();
    }

    @OnClick({R.id.cv_terima, R.id.cv_proses, R.id.cv_kirim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_terima:
                getActivity().startActivity(new Intent(getActivity(), RevTerimaActivity.class));
                break;
            case R.id.cv_proses:
                getActivity().startActivity(new Intent(getActivity(), RevProsesActivity.class));
                break;
            case R.id.cv_kirim:
                getActivity().startActivity(new Intent(getActivity(), RevKirimActivity.class));
                break;
        }
    }

    public void getCount(){

        //count revisi terima
        ApiService.service_get.getPelangganRev(sessionManager.getKeyId()).enqueue(new Callback<PelangganRevResponse>() {
            @Override
            public void onResponse(Call<PelangganRevResponse> call, Response<PelangganRevResponse> response) {
                if (response.code() == 200){
                    if (response.body().getCode() == 302){
                        tvCountterima.setText(String.valueOf(response.body().getList().size() - FormDataRevSaveHelper.getData().size()));

                    }else{
                        //Toast.makeText(getContext(), "Error " + response.body().getCode() + " : " + response.body().getMessaage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //Toast.makeText(getContext(), "Error " + response.code() + " : " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PelangganRevResponse> call, Throwable t) {
//                Toast.makeText(getContext(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                call.cancel();
            }
        });

        //count revisi proses
        tvCountproses.setText(String.valueOf(FormDataRevSaveHelper.getData().size()));

        //count revisi proses
        ApiService.service_get.getPelangganRevKirim(sessionManager.getKeyId()).enqueue(new Callback<PelangganRevKirimResponse>() {
            @Override
            public void onResponse(Call<PelangganRevKirimResponse> call, Response<PelangganRevKirimResponse> response) {
                if (response.code() == 200){
                    if (response.body().getCode() == 302 ){
                        tvCountkirim.setText(String.valueOf(response.body().getList().size()));
                    }
                    else {

                    }
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<PelangganRevKirimResponse> call, Throwable t) {

            }
        });
    }
}
