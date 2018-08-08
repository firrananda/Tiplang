package dev.zero.tiplangpdam.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.activity.baru.FormRealisasiProsesActivity;
import dev.zero.tiplangpdam.model.local.FormData;

public class ProsesSPKAdapter extends RecyclerView.Adapter<ProsesSPKAdapter.ViewHolder>{
    private List<FormData> listFormData;
    private Context context;

    public ProsesSPKAdapter(Context context, List<FormData> listFormData) {
        this.context = context;
        this.listFormData = listFormData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datapel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProsesSPKAdapter.ViewHolder holder, int position) {
        final FormData listitem = listFormData.get(position);

//        String textButton = listitem.getSpk() + " - " + listitem.getBatd();
//        holder.btnSpk.setText(textButton);
        holder.btnSpk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FormRealisasiProsesActivity.class);
//                intent.putExtra("Nomor SPK", String.valueOf(listitem.));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listFormData.size();
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
