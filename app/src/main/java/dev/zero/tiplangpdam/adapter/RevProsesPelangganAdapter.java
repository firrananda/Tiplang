package dev.zero.tiplangpdam.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.activity.baru.FormRealisasiProsesActivity;
import dev.zero.tiplangpdam.activity.revisi.FormRealProsesRevActivity;
import dev.zero.tiplangpdam.model.local.FormData;
import dev.zero.tiplangpdam.model.local.FormDataRev;

public class RevProsesPelangganAdapter extends RecyclerView.Adapter<RevProsesPelangganAdapter.ViewHolder> {
    private ArrayList<FormDataRev> listpelanggan;
    private Context context;

    public RevProsesPelangganAdapter(ArrayList<FormDataRev> listpelanggan, Context context) {
        this.listpelanggan = listpelanggan;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FormDataRev data = listpelanggan.get(position);
        holder.tvBatdpel.setText(data.getNo_batd());
        holder.tvTglBatd.setText(data.getTanggal_batd());
        holder.tvNopel.setText(data.getNo_pelanggan());
        holder.tvNamapel.setText(data.getNama_pelanggan());
        holder.tvZona.setText(data.getZona());
        holder.tvAlamat.setText(data.getJalan());

        final Intent intent = new Intent(context, FormRealProsesRevActivity.class);

        holder.btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("data", data);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.btn_entry)
        Button btnEntry;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
