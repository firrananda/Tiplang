package dev.zero.tiplangpdam.activity.baru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.adapter.KirimSPKAdapter;
import dev.zero.tiplangpdam.model.local.FormData;
import io.realm.Realm;
import io.realm.RealmResults;

public class KirimActivity extends AppCompatActivity {

    @BindView(R.id.rv_spkkirim)
    RecyclerView rvSPKKirim;
    Realm realm;
    KirimSPKAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baru_activity_kirim);
        ButterKnife.bind(this);
        rvSPKKirim.setLayoutManager(new LinearLayoutManager(this));
        rvSPKKirim.setHasFixedSize(true);
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<FormData> data = realm.where(FormData.class).findAll();
                ArrayList<FormData> listSPK = new ArrayList<>();
                for (int i = 0 ; i < data.size() ; i++){
                    if ( i == 0){
                        listSPK.add(data.get(i));
                    }else{
                        for (int j = 0 ; j< listSPK.size() ; j++){
                            if (!data.get(i).getSPK().equals(listSPK.get(j).getSPK())){
                                listSPK.add(data.get(i));
                            }
                        }
                    }
                }
                adapter = new KirimSPKAdapter(KirimActivity.this, listSPK);
                rvSPKKirim.setAdapter(adapter);
            }
        });


    }
}
