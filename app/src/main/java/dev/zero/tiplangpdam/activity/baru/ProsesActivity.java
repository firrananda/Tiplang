package dev.zero.tiplangpdam.activity.baru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.adapter.ProsesAdapter;
import dev.zero.tiplangpdam.adapter.SPKAdapter;
import dev.zero.tiplangpdam.model.local.FormData;
import io.realm.Realm;

public class ProsesActivity extends AppCompatActivity {

    @BindView(R.id.rv_spk)
    RecyclerView rvSpk;
    Realm realm;
    ProsesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baru_activity_proses);
        ButterKnife.bind(this);
        rvSpk.setLayoutManager(new LinearLayoutManager(this));
        rvSpk.setHasFixedSize(true);
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override public void execute(Realm realm) {
                List<FormData> data = realm.where(FormData.class).findAll();
                adapter = new ProsesAdapter(ProsesActivity.this,data);
                rvSpk.setAdapter(adapter);
            }
        });
    }
}
