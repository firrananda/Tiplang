package dev.zero.tiplangpdam.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.activity.baru.BarudataPelActivity;
import dev.zero.tiplangpdam.activity.baru.BarudataPelKirimActivity;
import dev.zero.tiplangpdam.activity.baru.KirimActivity;
import dev.zero.tiplangpdam.model.SPK;
import dev.zero.tiplangpdam.model.local.FormData;

public class KirimSPKAdapter extends  RecyclerView.Adapter<KirimSPKAdapter.ViewHolder> {
    private ArrayList<FormData> listformdata;
    private ArrayList<SPK> listspk;
    private Context context;

    public KirimSPKAdapter(Context context, ArrayList<FormData> listformdata, ArrayList<SPK> listspk) {
        this.context = context;
        this.listformdata = listformdata;
        this.listspk = listspk;
    }

    public KirimSPKAdapter(KirimActivity kirimActivity, ArrayList<FormData> listSPK) {
    }

    @Override
    public KirimSPKAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spk, parent, false);
        return new KirimSPKAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KirimSPKAdapter.ViewHolder holder, int position) {

        final FormData listitem = listformdata.get(position);
        final SPK listitem2 = listspk.get(position);

        String textButton = listitem.getSPK();
        holder.btnSpk.setText(textButton);
        holder.btnSpk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BarudataPelKirimActivity.class);
                intent.putExtra("SPK_ID", String.valueOf(listitem2.getId()));
                intent.putExtra("spk", listitem.getSPK());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listformdata.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_spk)
        Button btnSpk;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
