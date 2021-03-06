package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterTopMonVietDoAn extends RecyclerView.Adapter<AdapterTopMonVietDoAn.ViewHolderTopMonViet> {

    Context context;
    List<SanPham> sanPhamList;

    public AdapterTopMonVietDoAn(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @NonNull
    @Override
    public ViewHolderTopMonViet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_topmonviet,parent,false);

        ViewHolderTopMonViet viewHolderTopMonViet = new ViewHolderTopMonViet(view);

        return viewHolderTopMonViet;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTopMonViet holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getANHLON()).resize(130,135).centerInside().into(holder.imTopMonVietDoAn);

        holder.txtTenSP.setText(sanPham.getTENSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaTien.setText(gia +" VNĐ");

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolderTopMonViet extends RecyclerView.ViewHolder {
        ImageView imTopMonVietDoAn;
        TextView txtTenSP,txtGiaTien,txtGiamGia;

        public ViewHolderTopMonViet(@NonNull View itemView) {
            super(itemView);

            imTopMonVietDoAn = itemView.findViewById(R.id.imgTopMonVietDoAn);
            txtTenSP = itemView.findViewById(R.id.txtTieuDeTopMonVietDoAn);
            txtGiaTien = itemView.findViewById(R.id.txtGiaMonVietDoAn);
            txtGiamGia = itemView.findViewById(R.id.txtGiamGiaMonViet);
        }
    }
}
