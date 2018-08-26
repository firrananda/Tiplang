package dev.zero.tiplangpdam.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.activity.revisi.RevTerimaActivity;
import dev.zero.tiplangpdam.model.PelangganRev;

public class RevPelangganAdapter extends RecyclerView.Adapter<RevPelangganAdapter.ViewHolder> {

    private ArrayList<PelangganRev> listPelangganrev;
    private Context context;

    public RevPelangganAdapter(ArrayList<PelangganRev> listPelangganrev, Context context) {
        this.listPelangganrev = listPelangganrev;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datapel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PelangganRev listitem = listPelangganrev.get(position);

        holder.tvBatdpel.setText(listitem.getNomor_batd());
        holder.tvTanggalBatdpel.setText(listitem.getTanggal_batd());
        holder.tvNomorPel.setText(listitem.getNomor_pelanggan());
        holder.tvNamaPel.setText(listitem.getNama());
        holder.tvJalan.setText(listitem.getJalan());

        final Intent intent = new Intent(context, RevTerimaActivity.class);
        holder.btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPelangganrev.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_batdpel)
        TextView tvBatdpel;
        @BindView(R.id.tv_tgl_batd)
        TextView tvTanggalBatdpel;
        @BindView(R.id.tv_nopel)
        TextView tvNomorPel;
        @BindView(R.id.tv_namapel)
        TextView tvNamaPel;
        @BindView(R.id.tv_alamat)
        TextView tvJalan;
        @BindView(R.id.btn_entry)
        Button btnEntry;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
