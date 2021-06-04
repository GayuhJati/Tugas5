package com.example.tugas5_akbarajatigayuhrisangaji_124190047.view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tugas5_akbarajatigayuhrisangaji_124190047.R;
import com.example.tugas5_akbarajatigayuhrisangaji_124190047.entity.DataMotor;

import java.util.List;
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataMotor> list;
    MainContact.view mView;

    public MainAdapter(Context context, List<DataMotor> list, MainContact.view mView) {
        this.context = context;
        this.list = list;
        this.mView = mView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_motor,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.viewHolder holder, int position) {
        final DataMotor item = list.get(position);
        holder.tvNama.setText(item.getNama());
        holder.tvStnk.setText(item.getStnk());
        holder.tvTipe.setText(item.getTipe());
        holder.tvWarna.setText(item.getWarna());
        holder.id.setText(Integer.toString(item.getId()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mView.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvNama,tvStnk,tvTipe,tvWarna,id;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_item_nama);
            tvStnk = itemView.findViewById(R.id.tv_item_stnk);
            tvTipe = itemView.findViewById(R.id.tv_item_tipe);
            tvWarna = itemView.findViewById(R.id.tv_item_warna);
            id = itemView.findViewById(R.id.tv_item_id);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }
}
