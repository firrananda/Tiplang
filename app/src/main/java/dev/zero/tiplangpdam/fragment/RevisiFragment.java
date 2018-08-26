package dev.zero.tiplangpdam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.activity.revisi.KirimActivity;
import dev.zero.tiplangpdam.activity.revisi.ProsesActivity;
import dev.zero.tiplangpdam.activity.revisi.RevTerimaActivity;

public class RevisiFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.tv_countterima)
    TextView tvCountterima;
    @BindView(R.id.tv_countproses)
    TextView tvCountproses;
    @BindView(R.id.tv_countkirim)
    TextView tvCountkirim;

    public RevisiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_revisi, container, false);
        unbinder = ButterKnife.bind(this, view);

//        ApiService.service_get.getCountRevisi().enqueue(new Callback<CountRevisiResponse>() {
//            @Override
//            public void onResponse(Call<CountRevisiResponse> call, Response<CountRevisiResponse> response) {
//                if (response.body().getCode() == 302) {
//                    tvCountterima.setText(response.body().getBaru().toString());
//                    tvCountkirim.setText(response.body().getKirim().toString());
//                    Toast.makeText(getContext(), response.body().getMessaage(), Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(getContext(), response.body().getMessaage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CountRevisiResponse> call, Throwable t) {
//
//            }
//        });
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
                getActivity().startActivity(new Intent(getActivity(), RevTerimaActivity.class));
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
