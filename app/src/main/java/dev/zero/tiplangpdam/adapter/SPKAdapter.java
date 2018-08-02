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
import dev.zero.tiplangpdam.activity.baru.FormRealisasiActivity;
import dev.zero.tiplangpdam.model.SPK;

public class SPKAdapter extends RecyclerView.Adapter<SPKAdapter.ViewHolder> {

    private ArrayList<SPK> listSPK;
    private Context context;

    public SPKAdapter(Context context, ArrayList<SPK> listSPK) {
        this.context = context;
        this.listSPK = listSPK;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spk, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SPKAdapter.ViewHolder holder, int position) {
        final SPK listitem = listSPK.get(position);

        holder.btnSpk.setText(listitem.getNomor_spk());
        final Intent intent = new Intent(context, BarudataPelActivity.class);
        holder.btnSpk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("ID", String.valueOf(listitem.getId()));
                intent.putExtra("SPK", listitem.getNomor_spk());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSPK.size();
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

