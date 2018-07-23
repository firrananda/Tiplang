package dev.zero.tiplangpdam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.activity.baru.KirimActivity;
import dev.zero.tiplangpdam.activity.baru.ProsesActivity;
import dev.zero.tiplangpdam.activity.baru.TerimaActivity;

public class BaruFragment extends Fragment {
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baru, container, false);
        unbinder = ButterKnife.bind(this, view);
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
