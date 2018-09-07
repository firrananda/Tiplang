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
import dev.zero.tiplangpdam.activity.baru.FormRealisasiKirimActivity;
import dev.zero.tiplangpdam.activity.revisi.FormRealKirimRevActivity;
import dev.zero.tiplangpdam.model.PelangganRevKirim;

public class RevKirimPelangganAdapter extends RecyclerView.Adapter<RevKirimPelangganAdapter.ViewHolder> {

    private ArrayList<PelangganRevKirim> listpelanggan;
    private Context context;

    public RevKirimPelangganAdapter(ArrayList<PelangganRevKirim> listpelanggan, Context context) {
        this.listpelanggan = listpelanggan;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datapel_kirim, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PelangganRevKirim data = listpelanggan.get(position);

        holder.tvBatdpel.setText(data.getNomor_batd());
        holder.tvTglBatd.setText(data.getTanggal_batd());
        holder.tvNopel.setText(data.getNomor_pelanggan());
        holder.tvNamapel.setText(data.getNama());
        holder.tvZona.setText(data.getZona());
        holder.tvAlamat.setText(data.getJalan());

        final Intent intent = new Intent(context, FormRealKirimRevActivity.class);
        //intent.putExtra("id_form",22);
        intent.putExtra("datapel", data);
        intent.putExtra("FORM_ID", String.valueOf(data.getId_realisasi()));

        holder.btnLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listpelanggan.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_batdpel)
        TextView tvBatdpel;
        @BindView(R.id.tv_tgl_batd)
        TextView tvTglBatd;
        @BindView(R.id.tv_nopel)
        TextView tvNopel;
        @BindView(R.id.tv_namapel)
        TextView tvNamapel;
        @BindView(R.id.tv_zona)
        TextView tvZona;
        @BindView(R.id.tv_alamat)
        TextView tvAlamat;
        @BindView(R.id.btn_lihatdata)
        Button btnLihatData;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
