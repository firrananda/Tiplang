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
import dev.zero.tiplangpdam.activity.baru.FormRealisasiActivity;
import dev.zero.tiplangpdam.model.Pelanggan;

public class PelangganAdapter extends RecyclerView.Adapter<PelangganAdapter.ViewHolder> {

    private ArrayList <Pelanggan> listpelanggan;
    private Context context;

    public PelangganAdapter (Context context, ArrayList<Pelanggan> listpelanggan){
        this.context = context;
        this.listpelanggan = listpelanggan;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datapel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PelangganAdapter.ViewHolder holder, int position) {
        final Pelanggan listitem = listpelanggan.get(position);

        holder.tvnopel.setText(listitem.getNo_pelangan());
        holder.tvnamapel.setText(listitem.getNama());
        holder.tvzona.setText(listitem.getZona());
        holder.tvalamat.setText(listitem.getJalan()+listitem.getGang()+listitem.getNo_jalan()+listitem.getNo_tambahan());

        final Intent intent = new Intent(context, FormRealisasiActivity.class);

        holder.btnentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("ID", String.valueOf(listitem.getId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listpelanggan.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_nopel)
            TextView tvnopel;
        @BindView(R.id.tv_namapel)
            TextView tvnamapel;
        @BindView(R.id.tv_zona)
                TextView tvzona;
        @BindView(R.id.tv_alamat)
                TextView tvalamat;
        @BindView(R.id.btn_entry)
        Button btnentry;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
