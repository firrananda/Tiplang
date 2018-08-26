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
import dev.zero.tiplangpdam.activity.baru.BarudataPelKirimActivity;
import dev.zero.tiplangpdam.activity.baru.FormRealisasiKirimActivity;
import dev.zero.tiplangpdam.model.List_Realisasi;
import dev.zero.tiplangpdam.model.local.FormData;

public class KirimPelangganAdapter extends RecyclerView.Adapter<KirimPelangganAdapter.ViewHolder>{

    private ArrayList<List_Realisasi> listPelanggan;
    private Context context;

    public KirimPelangganAdapter(ArrayList<List_Realisasi> listPelanggan, Context context) {
        this.listPelanggan = listPelanggan;
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
        final List_Realisasi data = listPelanggan.get(position);
        holder.tvBatdpel.setText(data.getNomor_batd());
        holder.tvTglBatd.setText(data.getTanggal_batd());
        holder.tvNopel.setText(data.getNomor_pelanggan());
        holder.tvNamapel.setText(data.getNama());
        holder.tvAlamat.setText(data.getJalan());

        final Intent intent = new Intent(context, FormRealisasiKirimActivity.class);

        holder.btnLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPelanggan.size();
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
//        @BindView(R.id.tv_zona)
//        TextView tvZona;
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
