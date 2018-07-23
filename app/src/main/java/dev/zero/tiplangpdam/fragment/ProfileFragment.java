package dev.zero.tiplangpdam.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.activity.LoginActivity;
import dev.zero.tiplangpdam.service.SessionManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    SessionManager sessionManager;
    @BindView(R.id.foto)
    ImageView foto;
    @BindView(R.id.txv_nip)
    TextView txvNip;
    @BindView(R.id.txv_name)
    TextView txvName;
    Unbinder unbinder;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        sessionManager = new SessionManager(getContext());
        txvName.setText(sessionManager.getNama());
        txvNip.setText(sessionManager.getNip());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_logout)
    public void onViewClicked() {
        sessionManager.logoutUser();
        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}
