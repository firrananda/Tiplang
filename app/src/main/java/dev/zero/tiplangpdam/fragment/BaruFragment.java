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
import dev.zero.tiplangpdam.service.ApiService;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baru, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvCountproses.setText(String.valueOf(FormDataSaveHelper.getData().size()));

        ApiService.service_get.getCountBaru().enqueue(new Callback<CountBaruResponse>() {
            @Override
            public void onResponse(Call<CountBaruResponse> call, Response<CountBaruResponse> response) {
                if (response.code() == 200){
                    if (response.body().getCode() == 302){
                        tvCountkirim.setText(response.body().getKirim());
                    }
                    else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                else {
//                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CountBaruResponse> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cv_terima, R.id.cv_proses, R.id.cv_kirim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_terima:
                getActivity().startActivity(new Intent(getActivity(), TerimaActivity.class));
                break;
            case R.id.cv_proses:
                getActivity().startActivity(new Intent(getActivity(), ProsesActivity.class));
                break;
            case R.id.cv_kirim:
                getActivity().startActivity(new Intent(getActivity(), KirimActivity.class));
                break;
        }
    }
}
