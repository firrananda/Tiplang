package dev.zero.tiplangpdam.activity.baru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.adapter.PelangganAdapter;
import dev.zero.tiplangpdam.adapter.ProsesPelangganAdapter;
import dev.zero.tiplangpdam.adapter.ProsesSPKAdapter;
import dev.zero.tiplangpdam.model.local.FormData;
import io.realm.Realm;
import io.realm.RealmResults;

public class BarudataPelProsesActivity extends AppCompatActivity {

    @BindView(R.id.rv_datapel)
    RecyclerView rvDatapel;
    ProsesPelangganAdapter adapter;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barudata_pel_proses);
        ButterKnife.bind(this);
        String spk = getIntent().getStringExtra("spk");
        rvDatapel.setHasFixedSize(true);
        rvDatapel.setLayoutManager(new LinearLayoutManager(this));
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override public void execute(Realm realm) {
                RealmResults<FormData> data = realm.where(FormData.class).findAll();
                ArrayList<FormData> listSPK = new ArrayList<>();
                listSPK.addAll(data);
//                for (int i = 0; i < data.size(); i++) {
//                    if (i==0){
//                        listSPK.add(data.get(i));
//                    }else{
//                        for (int j = 0; j < listSPK.size(); j++) {
//                            if (!data.get(i).getSPK().equals(listSPK.get(j).getSPK())){
//                                listSPK.add(data.get(i));
//                            }
//                        }
//                    }
//                }
                adapter = new ProsesPelangganAdapter(listSPK,BarudataPelProsesActivity.this);
                rvDatapel.setAdapter(adapter);
            }
        });
    }
}
