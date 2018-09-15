package dev.zero.tiplangpdam.activity.revisi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.adapter.ProsesPelangganAdapter;
import dev.zero.tiplangpdam.adapter.RevProsesPelangganAdapter;
import dev.zero.tiplangpdam.model.local.FormData;
import dev.zero.tiplangpdam.model.local.FormDataRev;
import io.realm.Realm;
import io.realm.RealmResults;

public class RevProsesActivity extends AppCompatActivity {

    RevProsesPelangganAdapter adapter;
    Realm realm;
    @BindView(R.id.rv_proses)
    RecyclerView rvProses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revisi_activity_proses);
        ButterKnife.bind(this);

        rvProses.setHasFixedSize(true);
        rvProses.setLayoutManager(new LinearLayoutManager(this));
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<FormDataRev> dataRevs = realm.where(FormDataRev.class).findAll();
                Log.d("Data proses", "execute: " + dataRevs.size());
                ArrayList<FormDataRev> list = new ArrayList<>();
                list.addAll(dataRevs);
                adapter = new RevProsesPelangganAdapter(list, RevProsesActivity.this);
                rvProses.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        });
    }
}
